/**
 * Super simple cloudfunction to catch WSPR spots and pass them on.
 */
var request = require('request')

const Datastore = require('@google-cloud/datastore');
const datastore = new Datastore();

function validate(query) {
    if (query == null || !query) {
        return false
    }
    if (query.date == null || query.rcall == null) {
        return false
    }
    if(query.function != "wspr") {
        return false
    } 
    return true
}

exports.wsprGET = (req, res) => {
    
    if (validate(req.query)) {
        console.log(JSON.stringify(req.query))
        const key = datastore.key(['Spot']);
        datastore.save({
            key: key,
            data: req.query
          }, (err) => {
            if (!err) {
              // Record saved successfully.
              request({
                  url: 'http://wsprnet.org/post?',
                  qs: req.query
                }, (err, response, body) => {
                    if (err) { 
                        res.status(500).send(err);
                        return console.log(err); 
                        }
                    console.log(body.url);
                    console.log(body.explanation);
                    res.send(`${JSON.stringify(req.query)} ->  ${JSON.stringify(response)}`);
              }); 
            } else {
                res.status(500).send(JSON.stringify(err));
            }
          });
    } else {
        res.send(`${JSON.stringify(req.query)} is not valid`);
    }

};