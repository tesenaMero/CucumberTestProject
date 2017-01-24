Feature: COLAPLACEMENT
  Background:
    Given Pentaho is not stucked for CZ_Zaplo
    And Pentaho doesnt have more than 5 files to process for CZ_Zaplo

  @working
  Scenario: Cola account placement processing

    Given I create COLAAccountPlacement file for CZ_Zaplo
    When I put that file to cz/cola/pentaho/ folder
    Then Pentaho process that file
    Then File stored in cz/cola/pentaho/backup folder
  @working
  Scenario: Cola payment placement processing
    Given I create COLAPaymentPlacement file for CZ_Zaplo
    When I put that file to cz/cola/pentaho/ folder
    Then Pentaho process that file
    Then File stored in cz/cola/pentaho/backup folder