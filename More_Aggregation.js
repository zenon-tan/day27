use playstore

db.playstore.findOne()

db.playstore.aggregate([
    {
        $match:{"Rating": {$not: {$eq:NaN}}}
    },
    {
        $group:{_id:"$Category", app:{$push: {"App": "$App", "Rating": "$Rating", "Type": "$Type"}}}
            }

])

db.playstore.aggregate([
    {
        $match:{"Rating": NaN}
    }

])

db.playstore.aggregate([
    {
        $match:{"Rating": {$not: {$eq:NaN}}}
    },
    {
        $group:{_id:"$Category", app:{$push: {"App": "$App", "Rating": "$Rating", "Type": "$Type"}}}
        
    }

])
