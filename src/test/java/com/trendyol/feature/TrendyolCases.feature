Feature: TrendyolCases

  Scenario: Search "Harry Potter" and get id of "Harry Potter and the Sorcerer's Stone"
    Given Get API
    And Search "Harry+Potter"
    Then Check status code 200
    And Get id and check "Harry Potter and the Sorcerer's Stone"

  Scenario: Movie search with id or title

    Given Get API
    When Search "Harry+Potter"
    Then Check status code 200
    And Get id and check "Harry Potter and the Sorcerer's Stone"
    And Search by id
    Then Check title "Harry Potter and the Sorcerer's Stone"