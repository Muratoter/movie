/**
 * Created by muratoter on 09,December,2022
 */
object Dependencies {
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val daggerHilt by lazy { "com.google.dagger:hilt-android:${Versions.daggerHilt}" }
    val daggerHiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}" }
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
    val navigation by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}" }
    val navigationKtx by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val retrofitGsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val okHttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okHttp}" }
    val okHttpInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}" }
    val viewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }
    val dotsIndicator by lazy { "com.tbuonomo:dotsindicator:${Versions.dotsIndicator}" }
    val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
    val glideAnnotationProcessor by lazy { "com.github.bumptech.glide:compiler:${Versions.glide}" }
    val swipeToRefresh by lazy { "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeToRefresh}" }
    val paging by lazy{"androidx.paging:paging-runtime:${Versions.paging}"}
    val junit by lazy { "junit:junit:${Versions.jUnit4}" }
    val junitExt by lazy { "androidx.test.ext:junit:${Versions.jUnitExtensions}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
}