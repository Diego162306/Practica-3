from flask import Blueprint, abort, request, render_template, redirect, url_for # type: ignore

router = Blueprint('router', __name__)

@router.route('/')
def home():
    return render_template('tables.html')
