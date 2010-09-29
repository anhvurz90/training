#/bin/bash!

WORKING_DIR=`cd ${0%/*}/exo-training-package; pwd`
echo "Working directory : $WORKING_DIR"
#rm -rf $WORKING_DIR
mkdir -p $WORKING_DIR
mkdir -p $WORKING_DIR/repository
mkdir -p $WORKING_DIR/apps
mkdir -p $WORKING_DIR/binaries

if [ ! -e $WORKING_DIR/binaries/jdk-6u21-linux-i586.bin ]; then
wget -nc -nd -P $WORKING_DIR/binaries -r "http://cds.sun.com/is-bin/INTERSHOP.enfinity/WFS/CDS-CDS_Developer-Site/en_US/-/USD/VerifyItem-Start/jdk-6u21-linux-i586.bin?BundledLineItemUUID=7jSJ_hCuV.MAAAErwVYkgFh.&OrderID=kTGJ_hCuP3EAAAErrFYkgFh.&ProductID=LxaJ_hCy4mIAAAEpXLwzBGsB&FileName=/jdk-6u21-linux-i586.bin" -O jdk-6u21-linux-i586.bin
fi;
if [ ! -e $WORKING_DIR/binaries/jdk-6u21-linux-x64.bin ]; then
wget -nc -nd -P $WORKING_DIR/binaries -r "http://cds.sun.com/is-bin/INTERSHOP.enfinity/WFS/CDS-CDS_Developer-Site/en_US/-/USD/VerifyItem-Start/jdk-6u21-linux-x64.bin?BundledLineItemUUID=9XmJ_hCuJFAAAAEre2AkgFiC&OrderID=InqJ_hCusrEAAAEraWAkgFiC&ProductID=xKiJ_hCySHIAAAEpT7wzBGsB&FileName=/jdk-6u21-linux-x64.bin" -O jdk-6u21-linux-x64.bin
fi;
if [ ! -e $WORKING_DIR/binaries/jdk-6u21-windows-i586.exe ]; then
wget -nc -nd -P $WORKING_DIR/binaries -r "http://cds.sun.com/is-bin/INTERSHOP.enfinity/WFS/CDS-CDS_Developer-Site/en_US/-/USD/VerifyItem-Start/jdk-6u21-windows-i586.exe?BundledLineItemUUID=Rt6J_hCuWyIAAAErxrYkgFiC&OrderID=56SJ_hCuB3cAAAErvLYkgFiC&ProductID=h2iJ_hCyKS4AAAEpVrwzBGsB&FileName=/jdk-6u21-windows-i586.exe" -O jdk-6u21-windows-i586.exe
fi;
if [ ! -e $WORKING_DIR/binaries/jdk-6u21-windows-x64.exe ]; then
wget -nc -nd -P $WORKING_DIR/binaries -r "http://cds.sun.com/is-bin/INTERSHOP.enfinity/WFS/CDS-CDS_Developer-Site/en_US/-/USD/VerifyItem-Start/jdk-6u21-windows-x64.exe?BundledLineItemUUID=NjOJ_hCuax8AAAErfPckgFiC&OrderID=46SJ_hCuEr4AAAErcfckgFiC&ProductID=BXiJ_hCyf.MAAAEpc7wzBGsB&FileName=/jdk-6u21-windows-x64.exe" -O jdk-6u21-windows-x64.exe
fi;
if [ ! -e $WORKING_DIR/binaries/apache-maven-2.2.1-bin.zip ]; then
wget -nc -nd -P $WORKING_DIR/binaries -r "http://apache.cict.fr//maven/binaries/apache-maven-2.2.1-bin.zip"
fi;

rm -rf $WORKING_DIR/apps/apache-maven-2.2.1
unset M2_HOME
unzip $WORKING_DIR/binaries/apache-maven-2.2.1-bin.zip -d $WORKING_DIR/apps

svn export --force ${0%/*}/exo-developer $WORKING_DIR/exo-developer

function launchMaven {
  cd $1
  $WORKING_DIR/apps/apache-maven-2.2.1/bin/mvn "org.apache.maven.plugins:maven-dependency-plugin:2.1:go-offline" clean install clean -Dmaven.repo.local=$WORKING_DIR/repository
  if [ "$?" -ne "0" ]; then
    echo "!!! Sorry, maven build failed. Packaging aborted. !!!"
    exit 1
  fi
  cd -
}

#launchMaven  $WORKING_DIR/exo-developer/210-configuration/exercise-210-configuration-10-sharedLayout/1-project
#launchMaven  $WORKING_DIR/exo-developer/210-configuration/exercise-210-configuration-10-sharedLayout/2-solution
#launchMaven  $WORKING_DIR/exo-developer/210-configuration/exercise-210-configuration-12-jsp/1-project
#launchMaven  $WORKING_DIR/exo-developer/210-configuration/exercise-210-configuration-12-jsp/2-solution
#launchMaven  $WORKING_DIR/exo-developer/220-customization/exercise-230-customization-40-site-creation/2-solution
launchMaven  $WORKING_DIR/exo-developer/300-portlets/projects

rm exo-training.zip
zip -r exo-training.zip $WORKING_DIR/*
