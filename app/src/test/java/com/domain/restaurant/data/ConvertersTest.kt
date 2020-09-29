package com.domain.restaurant.data
import org.junit.Assert.assertEquals
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.domain.restaurant.modules.restaurant.data.models.UserRating
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class ConvertersTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun verifyListToJsonConversion() {
        val listOfStrings= listOf<String>("Charbel Makhlouf","Unit Test")
        val json=Converters().fromListOfStringToJson(listOfStrings)
        assertEquals(json,"[\"Charbel Makhlouf\",\"Unit Test\"]")
    }
    @Test
    fun verifyJsonToListConversion() {
        val safeList=Converters().fromJsonToListOfString("[\"Charbel Makhlouf\",\"Unit Test\"]")
        assertNotNull(safeList)
        assertEquals(safeList.size,2)
        assertEquals(safeList[0],"Charbel Makhlouf")
        assertEquals(safeList[1],"Unit Test")

    }
    @Test
    fun verifyUserRatingJsonConversion() {
        val userRating= UserRating(1,1.1,"Great","RED")
        val json=Converters().fromUserRatingToJson(userRating)
        assertEquals(json,"{\"votes\":1,\"aggregate_rating\":1.1,\"rating_text\":\"Great\",\"rating_color\":\"RED\"}")

    }

    @Test
    fun verifyJsonToMenuConversion() {
        val json="{\"votes\":1,\"aggregate_rating\":1.1,\"rating_text\":\"Great\",\"rating_color\":\"RED\"}"
        val userRating=Converters().fromJsonToUserRating(json)!!
        assertEquals(userRating.votes,1)
        assertEquals(userRating.aggregate_rating,1.1.toDouble(),0.toDouble())
        assertEquals(userRating.rating_color,"RED")
        assertEquals(userRating.rating_text,"Great")


    }

}