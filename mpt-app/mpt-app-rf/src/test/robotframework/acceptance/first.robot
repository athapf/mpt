
*** Settings ***
Library    Selenium2Library
Library    OperatingSystem
Library    DatabaseLibrary

Test Setup       Open test browser
Test Teardown    Close All Browsers


*** Variables ***

${LOGIN_FAIL_MSG}  Falsches Passwort


*** Test Cases ***

Start Application
    Go to    http://localhost:8086/mpt
    Title Should Be    MPT

Login with wrong password
    Go to    http://localhost:8086/mpt

    Page should contain element  id=form:nameInput
    Page should contain element  id=form:passwordInput
    Page should contain element  id=form:loginButton

    Input text  id=form:nameInput  anonymous
    Input text  id=form:passwordInput  secret12

    Click button  id=form:loginButton

    Page should contain  ${LOGIN_FAIL_MSG}

Register new user and login
    Connect To Database    org.postgresql.Driver    jdbc:postgresql://localhost:15432/mpt    mpt    mpt
    Execute SQL    DELETE FROM T_NICK_NAME WHERE ID >= 1000000;
    Execute SQL    COMMIT;
    Disconnect From Database

    Go to    http://localhost:8086/mpt

    Click link  id=form:registerLink

    Page should contain element  id=form:nameInput
    Page should contain element  id=form:nickInput
    Page should contain element  id=form:passwordInput
    Page should contain element  id=form:verifyInput
    Page should contain element  id=form:saveButton

    Input text  id=form:nameInput  Horst Bauer
    Input text  id=form:nickInput  hoba
    Input text  id=form:passwordInput  geheim123
    Input text  id=form:verifyInput  geheim123

    Click button  id=form:saveButton

    Page should contain element  id=form:nameInput
    Page should contain element  id=form:passwordInput
    Page should contain element  id=form:loginButton

    Input text  id=form:nameInput  hoba
    Input text  id=form:passwordInput  geheim123

    Click button  id=form:loginButton

    Page should contain  Overview

                 
*** Keywords ***

Open test browser
    Open browser  about:

Close test browser
    Close all browsers