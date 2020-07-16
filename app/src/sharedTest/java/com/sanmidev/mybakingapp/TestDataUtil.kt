package com.sanmidev.mybakingapp

import com.github.javafaker.Faker
import com.sanmidev.mybakingapp.data.local.BakingRecipeEntityList
import com.sanmidev.mybakingapp.data.local.BakingRecipeItemEntity
import com.sanmidev.mybakingapp.data.local.IngredientEntity
import com.sanmidev.mybakingapp.data.local.StepEntity
import com.sanmidev.mybakingapp.data.remote.model.BakingRecipeItemModel
import com.sanmidev.mybakingapp.data.remote.model.BakingRecipeModelList
import com.sanmidev.mybakingapp.data.remote.model.IngredientModel
import com.sanmidev.mybakingapp.data.remote.model.StepModel


object TestDataUtil {

    val faker = Faker()

    fun generateStepTestData(): Pair<StepModel, StepEntity> {
        val description = faker.food().dish()
        val id = faker.number().digits(2).toInt()
        val shortDescription = faker.book().title()
        val thumbNail = faker.internet().url()
        val videoURL = faker.internet().url()

        val stepModel = StepModel(description, id, shortDescription, thumbNail, videoURL)


        val stepEntity = StepEntity(description, id, shortDescription, thumbNail, videoURL)

        return Pair(stepModel, stepEntity)
    }

    fun generateIngredientTestData(): Pair<IngredientModel, IngredientEntity> {

        val ingredient = faker.food().ingredient()
        val measure = faker.food().measurement()
        val quantity = faker.number().digits(2).toInt()

        val ingredientModel = IngredientModel(ingredient, measure, quantity)

        val ingredientEntity = IngredientEntity(ingredient, measure, quantity)

        return Pair(ingredientModel, ingredientEntity)
    }

    fun generateBakingRecipeTestData(): Pair<BakingRecipeItemModel, BakingRecipeItemEntity> {

        val id = faker.number().digits(3).toInt()
        val image = faker.internet().url()
        val ingredientsModelList: MutableList<IngredientModel> = mutableListOf()
        val ingredientEntityList: MutableList<IngredientEntity> = mutableListOf()
        val name = faker.food().dish()
        val servings = faker.number().digits(2).toInt()
        val stepModelList: MutableList<StepModel> = mutableListOf()
        val stepEntityList: MutableList<StepEntity> = mutableListOf()

        repeat(10) {
            val ingredientTestData = generateIngredientTestData()

            ingredientsModelList.add(ingredientTestData.first)
            ingredientEntityList.add(ingredientTestData.second)

            val stepTestData = generateStepTestData()

            stepModelList.add(stepTestData.first)
            stepEntityList.add(stepTestData.second)

        }

        val bakingRecipeItemEntity: BakingRecipeItemEntity =
            BakingRecipeItemEntity(id, image, ingredientEntityList, name, servings, stepEntityList)

        val bakingStepModel: BakingRecipeItemModel =
            BakingRecipeItemModel(id, image, ingredientsModelList, name, servings, stepModelList)

        return Pair(bakingStepModel, bakingRecipeItemEntity)
    }

    fun generateBakingRecipeListTestData(): Pair<BakingRecipeModelList, BakingRecipeEntityList> {

        val bakingRecipeModelList = BakingRecipeModelList()
        val bakingRecipeEnities: MutableList<BakingRecipeItemEntity> = mutableListOf()

        repeat(10) {
            val testData = generateBakingRecipeTestData()

            bakingRecipeModelList.add(testData.first)
            bakingRecipeEnities.add(testData.second)
        }

        val bakingRecipeEntityList = BakingRecipeEntityList(bakingRecipeEnities)

        return Pair(bakingRecipeModelList, bakingRecipeEntityList)

    }
}