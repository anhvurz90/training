This is the maven archetype project for use as the starter kit for eXo Portal training labs
To learn more about maven archetypes read : http://maven.apache.org/plugins/maven-archetype-plugin/

In order to use this archetype, it must be stored in your maven repository. 
To install it in your local repository, simply run : mvn clean install in this folder.

Once installed, you can create a new bootstrap maven project for the labs exercices by running : 

mvn archetype:create -DarchetypeGroupId=org.exoplatform.training.portal -DarchetypeArtifactId=starterkit
-DarchetypeVersion=1.0-SNAPSHOT -DgroupId=org.exoplatform.training.portal -DartifactId=lab-XY

Then you can go to your project and build it
 cd lab-XY
 mvn clean install
 
