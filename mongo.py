from flask import Flask, jsonify, request
from flask_pymongo import PyMongo

app = Flask(__name__)

app.config['MONGO_DBairline'] = 'mongo_connect'
app.config['MONGO_URI'] = 'mongodb://wmy:ilovecd@ds251799.mlab.com:51799/mongo_connect'

mongo = PyMongo(app)

@app.route('/flights', methods=['GET'])
def get_all_flights():
    flights = mongo.db.flights 

    output = []

    for q in flights.find():
        output.append({'airline' : q['airline'], 'arrival' : q['arrival']})

    return jsonify({'result' : output})

@app.route('/flights/<airline>', methods=['GET'])
def get_one_flight(airline):
    flights = mongo.db.flights

    q = flights.find_one({'airline' : airline})

    if q:
        output = {'airline' : q['airline'], 'arrival' : q['arrival']}
    else:
        output = 'No results found'

    return jsonify({'result' : output})

@app.route('/flights', methods=['POST'])
def add_framework():
    flights = mongo.db.flights 

    airline = request.json['airline']
    arrival = request.json['arrival']

    flights = flights.insert({'airline' : airline, 'arrival' : arrival})
    new_framework = flights.find_one({'_id' : flights_id})

    output = {'airline' : new_framework['airline'], 'arrival' : new_framework['arrival']}

    return jsonify({'result' : output})

if __name__ == '__main__':
    app.run(debug=True)