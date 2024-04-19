#import random
from flask import Flask, render_template

app = Flask(__name__)

# function will run when at url "/"
@app.route("/")
def home():
    return render_template("app.html")