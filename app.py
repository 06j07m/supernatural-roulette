"""
flask app related
"""

from flask import Flask, render_template, request
import main

app = Flask(__name__)


# Name of data file
FILEPATH = "files/episodes.xlsx"

# open excel file and get list of all episodes
all_eps = main.get_all_episodes(FILEPATH)


# display main page html at url "/"
@app.route("/", methods=['GET','POST'])
def home():
    ep = ""
    if request.method == 'POST':
        # check for post request
        if "submit-controls" in request.form:
            # if form is submitted, get entered data
            input_seasons = request.form["seasons"]
            input_crack = request.form["crack"]

            # parse the data
            seasons, crack = main.parse_data(input_seasons, input_crack)

            # generate random episode
            ep = main.get_random_episode(all_eps, seasons, crack)
            print(ep)

    return render_template("app.html", generated_ep = ep)