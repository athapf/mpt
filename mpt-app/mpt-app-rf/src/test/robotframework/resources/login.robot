
*** Keywords ***

Open test browser
    Open browser  about:

Close test browser
    Close all browsers

RegisterPage should be shown
    Page should contain element  css=*[id$='nameInput']
    Page should contain element  css=*[id$='nickInput']
    Page should contain element  css=*[id$='passwordInput']
    Page should contain element  css=*[id$='verifyInput']
    Page should contain element  css=*[id$='saveButton']

Register new user
    Input text  css=*[id$='nameInput']  Horst Bauer
    Input text  css=*[id$='nickInput']  hoba
    Input text  css=*[id$='passwordInput']  geheim123
    Input text  css=*[id$='verifyInput']  geheim123

    Click button  css=*[id$='saveButton']

LoginPage should be shown
    Page should contain element  css=*[id$='nameInput']
    Page should contain element  css=*[id$='passwordInput']
    Page should contain element  css=*[id$='loginButton']

Login with user
    [Arguments]    ${USER}    ${PASSWORD}
    Input text  css=*[id$='nameInput']  ${USER}
    Input text  css=*[id$='passwordInput']  ${PASSWORD}

    Click button  css=*[id$='loginButton']

Remove user from database
    Connect To Database    org.postgresql.Driver    jdbc:postgresql://localhost:15432/mpt    mpt    mpt
    Execute SQL    DELETE FROM T_NICK_NAME WHERE NICK = 'hoba';
    Execute SQL    COMMIT;
    Disconnect From Database
