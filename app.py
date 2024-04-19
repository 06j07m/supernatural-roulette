"""
makes and runs flask app
"""

from flask import Flask, render_template

app = Flask(__name__)

# display main page html at url "/"
@app.route("/", methods=['GET','POST'])
def home():
    return render_template("app.html")