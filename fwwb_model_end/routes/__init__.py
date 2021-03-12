from .test import test_bp


def init_app(app):
    app.register_blueprint(test_bp)
