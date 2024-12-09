package com.example.data.repository

import com.example.data.source.FavoriteDBDataSource
import com.example.data.source.RecipeNetworkDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class RecipeRepositoryImplTest {

    private val recipeNetworkDataSource: RecipeNetworkDataSource = mock()
    private val favoriteDBDataSource: FavoriteDBDataSource = mock()

    private val repository = RecipeRepositoryImpl(
        recipeNetworkDataSource = recipeNetworkDataSource,
        favoriteDBDataSource = favoriteDBDataSource,
    )

    @Test
    fun `when repository getRecipeByName() expect recipeNetworkDataSource getRecipeByName()`() {
        runBlocking {
            `when`(recipeNetworkDataSource.getRecipeByName(any()))
                .thenReturn(ModelsMock.catalogDataModel)
            repository.getRecipeByName(ModelsMock.searchRequestModel)
            verify(recipeNetworkDataSource).getRecipeByName(any())
        }
    }

    @Test
    fun `when repository getCatalog() expect recipeNetworkDataSource getCatalog()`() {
        runBlocking {
            `when`(recipeNetworkDataSource.getCatalog())
                .thenReturn(ModelsMock.catalogDataModel)
            repository.getCatalog()
            verify(recipeNetworkDataSource).getCatalog()
        }
    }

    @Test
    fun `when repository getDetails() expect recipeNetworkDataSource getDetails()`() {
        runBlocking {
            `when`(recipeNetworkDataSource.getIngredients(any())).thenReturn(ModelsMock.detailsIngredientsDataModel)
            `when`(recipeNetworkDataSource.getSteps(any())).thenReturn(ModelsMock.detailsStepsDataModel)
            `when`(favoriteDBDataSource.checkFavorite(any())).thenReturn(any())
            repository.getDetails(ModelsMock.recipeModel)
            verify(recipeNetworkDataSource).getIngredients(any())
            verify(recipeNetworkDataSource).getSteps(any())
            verify(favoriteDBDataSource).checkFavorite(any())
        }
    }

    @Test
    fun `when repository addFavorite() expect favoriteDBDataSource addFavorite()`() {
        runBlocking {
            repository.addFavorite(ModelsMock.recipeModel)
            verify(favoriteDBDataSource).addFavorite(any())
        }
    }

    @Test
    fun `when repository removeFavorite() expect favoriteDBDataSource removeFavorite()`() {
        runBlocking {
            repository.removeFavorite(ModelsMock.recipeModel)
            verify(favoriteDBDataSource).removeFavorite(any())
        }
    }

    @Test
    fun `when repository getFavoriteRecipes() expect favoriteDBDataSource getFavoriteRecipe()`() {
        runBlocking {
            `when`(favoriteDBDataSource.getFavoriteRecipes())
                .thenReturn(ModelsMock.favoriteRecipeDataModel)
            repository.getFavoriteRecipes()
            verify(favoriteDBDataSource).getFavoriteRecipes()
        }
    }
}
