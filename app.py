"""
makes and runs flask app
"""

from flask import Flask, render_template, request

app = Flask(__name__)

# display main page html at url "/"
@app.route("/", methods=['GET','POST'])
def home():
    if request.method == 'POST':
        incl_seasons = request.form["seasons"]
        incl_crack = request.form["crack"]
        print(incl_seasons, incl_crack)
    return render_template("app.html")