"""
flask app related
"""

from flask import Flask, render_template, request
import main

app = Flask(__name__)


# Name of data file
FILEPATH = "data/episodes.xlsx"

# open excel file and get list of all episodes
all_eps = main.get_all_episodes(FILEPATH)


# # display main page html at url "/"
@app.route("/", methods=['GET','POST'])
def home():
    ep = ""
    # default form values
    inputs = ["1-15", "0"]
    if request.method == 'POST':
        # check for post request
        if "form1" in request.form:
            # if form is submitted, get entered data
            input_seasons = request.form["seasons"]
            input_crack = request.form["crack"]

            # parse the data
            try:
                seasons, crack = main.parse_data(input_seasons, input_crack)
                # generate random episode
                try:
                    ep = main.get_random_episode(all_eps, seasons, crack)
                # print error message if can't be generated
                except IndexError:
                    ep = "Error: no episodes according to current filters"
            # if the seasons format is not right
            except ValueError:
                ep = "Error: invalid format for \"Seasons\""

            inputs = [input_seasons, input_crack]

    return render_template("app.html", 
                           generated_ep = ep, 
                           inputs = inputs)