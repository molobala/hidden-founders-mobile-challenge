##Hidden Founders Mobile coding challenge
A challenge proposed by Hidden Founders ([challenge on github here](https://github.com/hiddenfounders/mobile-coding-challenge))
##Overview
The challenge consists of making an Android or Ios Application
listing the top trending repositories on Github in the last 30 days
using Github REST API.  
As user we should be able to :
* See top trending repositories on github ordered by stars number desc
 and by one repository per row
* See for each repo/row the following details :
  * Repository name
  * Repository description
  * Numbers of stars for the repo.
  * Username and avatar of the owner.
* to keep scrolling and new results should appear (pagination).  
 
I used that endpoint [https://api.github.com/search/repositories?q=created](https://api.github.com/search/repositories?q=created:>2017-12-21&sort=stars&order=desc)
to get json data from github.
## Coding context
### Development tools
I used [Jetbrains Intellij IDEA](https://www.jetbrains.com/idea) to make that application, but you are free to use 
your favorite IDEA, you just need to search about how to import an Intellij IDEA
project into your IDEA.

### How to compile
Assume you have those dependencies in your gradle if using a gradle project
```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.android.support:appcompat-v7:26.+'
    compile 'com.android.support:design:26.+'
    compile 'com.android.support:support-vector-drawable:26.+'
    compile 'com.android.support:support-v4:26.+'
    compile 'com.mcxiaoke.volley:library:1.0.19'
    testCompile 'junit:junit:4.12'
}
```
**volley library is used for network interaction**  

If you don't use the code in a gradle project you may download the required libraries files from the net 
to add them to the project.

##Perspectives
There are many things to improve in that application such as the images caching system,
use of recycleview  instead of listView to display repositories,... 