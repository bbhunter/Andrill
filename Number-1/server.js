var express = require('express');
var app = express();
var fs = require("fs");
const lowdb = require('lowdb');
const FileSync = require('lowdb/adapters/FileSync')
const adapter = new FileSync('database.json')
const db = lowdb(adapter)
const md5 = require('md5');
const uuidv1 = require('uuid/v1');

function prepare_result(status, data) {
    data.status = status;
    return JSON.stringify(data)
}

function current_time() {
    return Math.floor(Date.now() / 1000)
}

app.use(express.json());
app.use(function (req, res, next) {
    res.writeHead(200, {
        "Content-Type": "application/json"
    });

    next()
})

app.post('/authenticate', function (req, res) {

    var login_token = uuidv1();
    var login_expires = current_time() + 3600

    var login = db.get('credentials').find({
        user: req.body.username,
        pass: md5(req.body.password)
    })

    if (login.value() !== undefined) {

        login.assign({
            "login_token": {
                "value": login_token,
                "expires": login_expires
            }
        }).write()

        res.end(prepare_result('success', {
            'login_token': login_token
        }));

    } else {
        res.end(prepare_result('failed', {
            'reason': 'Invalid credentials.'
        }));

    }

});

app.post('/back-end', function (req, res) {

    var information = db.get('credentials').find({
        login_token: {
            "value": req.body.auth_token
        }
    }).value()

    if (information !== undefined) {

        if (information.login_token.expires > current_time()) {
            var login = true;
            var action = req.body.action

            if (action == 'get_admin_secret') {

                res.end(prepare_result('success', {
                    'admin_notes': db.get('admins_secret').find().value().notes
                }));

            } else if (action == 'get_contents') {
                res.end(prepare_result('success', {
                    'user_notes': db.get('users_notes').find().value().notes
                }));
            } else {

                res.end(prepare_result('failed', {
                    'reason': 'Invalid action.'
                }));
            }

        } else {
            res.end(JSON.stringify({
                'result': 'failed',
                'reason': 'The token has expired.'
            }))
        }
    }else{
        res.end(JSON.stringify({
            'result': 'failed',
            'reason': 'Invalid token was provided.'
        }))
    }
});


app.get('*', function (req, res) {
    res.sendStatus(404);
});


var server = app.listen(8081, function () {
    var host = server.address().address
    var port = server.address().port
    console.log("Example app listening at http://%s:%s", host, port)
})