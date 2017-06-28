
*** Settings ***
Library    de.thaso.mpt.app.rf.SimpleKeywordLibrary

Force Tags  queue

*** Test Cases ***
Testing a FIFO queue

Create and test quueue
    [Tags]  quick
    
    create an empty queue
    add an element  "Hello"
    add an element  "World"
    the first element should be  "Hello"
    pull element
    the first element should be  "World"
