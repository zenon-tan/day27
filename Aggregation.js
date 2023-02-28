//use shows

//db.tv.aggregate([
//    {
//        $match:{language:{$regex: 'english', $options:'i'}}//    },
//    {
//        $project: {_id:0, url:1, genres: 1}
//        //    },
//    {
//        $limit: 5//    }
//])

// $runtime -> is a value not the name
// we want the _id to be the runtime values
// if the name appears on the right side, its a value so its a $runtime
// equivalent of select runtime by tv group by runtime in sql

// to access composite key -> _id.language, _id.runtime

//db.tv.aggregate([
//    {
//        $group: {_id: {runtime:"$runtime", language:"$language"}}
//    
//    }
//])

//total -> counting


db.tv.aggregate([
    {
        $group: {_id: "$runtime",
            shows: {$push: {title: "$name", language:"$language"}},
            total: {$sum: 1}, avgRating: {$avg: "$rating.average"}}
    },
    {
        $match:{_id:{$gte:50}}      }
])

db.tv.aggregate([
    {
        $project: {
            _id:1, url:1,
            title: "$name"        }
        }
])

db.tv.aggregate([

    {
        $project: {
            _id:1, url:1,
            title: {$concat: ["$name", " (", {$toString: "$runtime"}, " mins)"]}
        }
    
    }
])

db.tv.findOne()

// unwinding

db.tv.aggregate([
    {
        $unwind: "$genres"
        },
    {
        $group:{_id: "$genres", total: {"$sum": 1}}    },
    {
        $sort:{_id: 1}    }
    
])

db.tv.aggregate([
    {
        $bucket: {
            groupBy: "$rating.average",
            boundaries: [3, 6, 9],
            default: '>9'        }
            
    }
])

use bgg

// $lookup

db.comments.findOne()

db.game.aggregate([
    {
        $match: {name: {$regex: "^carcassonne$", $options:"i"}}    },
    {
        $lookup: {from: "comments", foreignField:"gid", localField: "gid", as: "comments"}
    },
    {
        $unwind: "$comments"    },
    // Use pipeline
    {
        $sort: {"comments.rating": -1}
    },
    {
        $limit: 5
    }
    
])




