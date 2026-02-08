package com.fastcampusmall.data.repository

import com.fastcampusmall.data.datasource.ProductDataSource
import com.fastcampusmall.domain.model.Category
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
) : CategoryRepository {
    override fun getCategories(): Flow<List<Category>> = flow {
        emit(
            listOf(
                Category.Top,
                Category.Dress,
                Category.OuterWear,
                Category.Pants,
                Category.Shoes,
                Category.Skirt,
                Category.Bag,
                Category.FashionAccessories
            )
        )
    }

    override fun getProductsByCategory(category: Category): Flow<List<Product>> {
        return dataSource.getHomeComponents().map { list ->
            list.filterIsInstance<Product>().filter { product ->
                product.category.categoryId == category.categoryId
            }
        }
    }
}