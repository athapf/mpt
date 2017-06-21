
*** Settings ***
Library    Selenium2Library
Library    OperatingSystem
Library    DatabaseLibrary

Resource   ../resources/login.robot

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

    LoginPage should be shown
    Login with user  anonymous  secret12

    Page should contain  ${LOGIN_FAIL_MSG}


Register new user and login
    Remove user from database

    Go to    http://localhost:8086/mpt
    Click link  css=*[id$='registerLink']

    RegisterPage should be shown
    Register new user

    LoginPage should be shown
    Login with user  hoba  geheim123

    Page should contain  Overview