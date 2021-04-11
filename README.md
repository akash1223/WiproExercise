# WiproExercise

| Branch-> feed-screen     | Branch -> feed-screen-ui-changes   |
|------------|-------------|
| ![Branch-> feed-screen](../develop/screenshot/ScreenshotFeeds1.jpg) | ![Branch -> feed-screen-ui-changes](../develop/screenshot/ScreenshotFeeds2.jpg) |

#### The app has following packages:

1. **di**: It contains the files required by Koin
2. **http**: It contains all retrofit and network call.
3. **model**: It contains all Response and Request model.
4. **repository**: It contains repository class.
5. **ui**: View classes along with their corresponding ViewModel.
6. **utils**: Utility classes.

## Specs & Open-source libraries
- Minimum SDK 25
- MVVM Architecture
- Architecture Components (LiveData, ViewModel,Navigation)
- DataBinding
- Coroutine for Network call
- Unit test demonstration using JUnit and Mockito
- Listadapter for List

  ## dependencies
- [Koin]
- [Retrofit2 & Gson] for constructing the REST API
- [Glide] for loading images
- [Mockito] for Junit mock test


# How to build ?

Open terminal and type the below command to generate debug build <br/>

``` ./gradlew assembleDebug ```

Open terminal and type the below command to generate release build <br/>

``` ./gradlew assembleRelease ```

# How to generate code coverage report ?

Open terminal and type the following command

```./gradlew clean jacocoTestReport```

The coverage report will be generated on the following path.

``` app/build/reports ```


