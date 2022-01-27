package ru.yandex.iwithu.dao

import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository
import ru.yandex.iwithu.model.IdSequence

/**
@author ugoryntsev
 */
@Repository
class SequenceDao(
    private val mongoOperations: MongoOperations
) {

    fun getNextSequenceId(key: String): Long {

        val query = Query(Criteria.where("_id").`is`(key))
        val update = with (Update()) {
            inc("seq", 1)
            this
        }

        if (!mongoOperations.exists(query, IdSequence::class.java)) {
            mongoOperations.save(IdSequence(key, 0L))
        }

        val options = with(FindAndModifyOptions()) {
            returnNew(true)
            this
        }

        val idSequence =
            mongoOperations.findAndModify(query, update, options, IdSequence::class.java)
        ?: mongoOperations.save(IdSequence(key, 0L))

        return idSequence.seq
    }
}