use bgg;

db.comment.findOne();
db.game.findOne();

db.comment.find({
    
    "gid": 2.0
});

use playstore;

db.game.find()
.limit(25);

db.comment.find()
.limit(25);

db.comment.insert({
    "c_id": "tt88hh66",
    "user": "Jesus",
    "rating": 10,
    "c-text": "May GodGa bless you",
    "gid": 386})

db.comment.update(
    {"c_id": "tt88hh66"},
    {
        $set: {"c-text": "May God Blast U"}    }
)

db.reviews.find();
