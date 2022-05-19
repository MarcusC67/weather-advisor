# Deploy package to AWS using Elastic Beanstalk

## Create Jar for the project

Use Maven package - see [Docker - Build the Maven package in the IntelliJ](./docker.md#build-the-maven-package-in-the-intellij)

## Package using docker

See [Docker - Packaging](./docker.md#packaging)

## Zip up package

Not sure if it is all required, but create a Zip in the project base directory, including all the (not hidden) files.
I.e. ignore files and directories starting with "."!

## Create Elastic Beanstalk application

* Navigate to [AWS Elastic Beanstalk](https://eu-west-2.console.aws.amazon.com/elasticbeanstalk/home?region=eu-west-2#/welcome)
  * eu-west-2 is Europe(London)
* Use the "Create Application" button
  * Fill in "Application name"
  * Select "Platform" - "Docker", and it's defaults
  * Select "Application code" - "Upload your code"
  * Fill in "Source code origin" - "Version label"
    * This is prefilled with the Application name above - just add a version to it
  * Use the "Choose File" button to select the Zip File created above
    * wait a few moments to the upload
  * Use the "Configure more options"
    * Under the "Category" of "Software"
    * Press the "Edit" button
    * In the Environment properties section...
        * Right at the bottom is the "Environment properties" section
        * Add the key-value pairs for the environment properties that you want to use
        * "Create application" button
  * Or else just create the application
      * Use the "Create application" button to start the build
      * This will take a few minutes (5-ish)
      * **Note** this will probably fail as we require an environment variable

## Add and Environment Variable to the application 

Using the Elastic Beanstalk console:
1. Open the Elastic Beanstalk console.
2. Find your application:
   1. On the left menu plane select "Applications"
   2. In "All applications" - click on the name of the application you want to update (may only be one)
3. Select your application
   1. Select the "Environment name" you want to change(probably only one)
4. On the left menu plane select "Configuration"
5. For the Software category, choose Modify:
   1. Under the "Category" column, look for "Software"
   2. Press the "Edit" button
6. In the Environment properties section...
   1. Right at the bottom is the "Environment properties" section
   2. Add the key-value pairs for the environment properties that you want to use
7. Choose "Apply"
8. The application should restart and all being well come up green
