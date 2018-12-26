const express = require('express');
const app = express();
const lowdb = require('lowdb');
const FileSync = require('lowdb/adapters/FileSync')
const adapter = new FileSync('database.json')
const db = lowdb(adapter)
const md5 = require('md5');
const uuidv1 = require('uuid/v1');

// some functions
function prepare_result(status, data) {
    data.status = status;
    return JSON.stringify(data)
}

function current_time() {
    return Math.floor(Date.now() / 1000)
}

// using JSON
app.use(express.json());

// make content-type application/json for the responses
app.use(function (req, res, next) {
    res.writeHead(200, {
        "Content-Type": "application/json"
    });
    next()
})

// authentication end-point
app.post('/authenticate', function (req, res) {

    var login_token = uuidv1();
    var login_expires = current_time() + 3600

    // checking the credentials
    var login = db.get('credentials').find({
        user: req.body.username,
        pass: md5(req.body.password)
    })

    // log-in successfully
    if (login.value() !== undefined) {

        // saving token in the database
        login.assign({
            "login_token": {
                "value": login_token,
                "expires": login_expires
            }
        }).write()

        // making response
        var login = login.value()
        res.end(prepare_result('success', {
            'user_id': login.id,
            'display_name': login.display_name,
            'login_token': login_token
        }));

    } else {
        // invalid credentials
        res.end(prepare_result('failed', {
            'reason': 'Invalid credentials.'
        }));

    }

});

// back-end end-point for the authenticated users
app.post('/back-end', function (req, res) {
    
    // checking the token
    var information = db.get('credentials').find({
        login_token: {
            "value": req.body.auth_token
        }
    }).value()

    // valid token
    if (information !== undefined) {

        // checking the expiration
        if (information.login_token.expires > current_time()) {

            var action = req.body.action

            // administrator notes
            if (action == 'get_admin_secret') {
                res.end(prepare_result('success', {
                    'admin_notes': db.get('admins_secret').find().value().notes
                }));

                // users contents 
            } else if (action == 'get_contents') {
                res.end(prepare_result('success', {
                    'user_notes': db.get('users_notes').find().value().notes
                }));
            } else {

                // invalid action
                res.end(prepare_result('failed', {
                    'reason': 'Invalid action.'
                }));
            }

        } else {
            res.end(prepare_result('failed', {
                'reason': 'The token has expired.'
            }))
        }
    } else {
        res.end(prepare_result('failed', {
            'reason': 'Invalid token was provided.'
        }))
    }
});

// everything else = 404 not found
app.get('*', function (req, res) {
    res.sendStatus(404);
});


// intiating the server
var server = app.listen(8081, function () {
    var host = server.address().address
    var port = server.address().port
    console.log("Example app listening at http://%s:%s", host, port)
})