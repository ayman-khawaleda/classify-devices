# Model:

checkout the notebooks directory.

## DRF - Run Classification API:

The Model API was writen in  DRF.

## Run Project:

You should be in model_api to run the server.

### On Linux:

1. python -m venv .venv
2. source .venv/bin/activate
3. pip install -r requirements.txt
4. cd api
5. python manage.py runserver

### On windows:

1. python -m venv .venv
2. .\\\.venv\scripts\activate
3. pip install -r requirements.txt
4. cd api
5. python manage.py runserver

## Documentation

Check url : `http://127.0.0.1:8000/api/redoc/`

Or url:` http://127.0.0.1:8000/api/swagger/`

## Run tests:

#### Spring Test:

Follow the instrections in SpringBackend/README.md

Or you can open new terminal and activate the environment as before and cd to test directory and run:

```
python tests.py
```

# Spring Backend

## Getting Started

### Run backend

First of all make sure that the model's api run as descreped in model_api/README.md

```
./gradlew bootRun
```

### Guides

#### Check Device Backend Docs:

```
http://localhost:8080/swagger-ui/index.html
```

Run Tests

```
./gradlew test
```
