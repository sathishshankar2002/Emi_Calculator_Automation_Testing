Feature: Loan Calculator Testing In Edge Browser
	
	# This feature tests the loan calculator functionalities in the Edge browser, including car loans, home loans, and EMI calculations.

  @carLoan
  Scenario: Calculate_Car_Loan
  		# Open the browser and navigate to the car loan section
	    Given the browser "Edge" is opened
	    When navigating to the car loan section
	    
	    # Enter the necessary details for the car loan calculation
	    And entering the car loan details
	    
	    # Scroll through the options and select the car loan duration in years
	    And scrolling and clicking car loan years
	    
	    # Extract and filter the car loan data
	    Then extracting car loan yearly data
	    And extracting car loan monthly data
	    And filtering car loan data
	    
	    # Print the filtered car loan data for verification
	    And printing the filtered car loan data
	    
	    # Confirm that the car loan calculation is completed
	    Given the car loan calculation is completed
   
   @homeLoan 
   @depends(on="Calculate_Car_Loan")  
   Scenario: Calculate_Home_Loan
   		# Navigate to the home loan section and enter details
    	When navigating to the home loan section
    	
    	# Enter the necessary details for the home loan calculation
    	And entering the home loan details
    	
    	# Scroll through the options and select the home loan duration in years
	    And scrolling and clicking home loan years
	    
	    # Extract and store the home loan data
	    Then extracting home loan yearly table data
	    And storing the home loan values in an Excel sheet
	    
	    # Confirm that the home loan EMI calculation is completed
	    Given the home loan EMI calculation is completed 
	    
	@emiCalculator
	@depends(on="Calculate_Home_Loan")
	Scenario: Testing EMI Calculator
	    # Navigate to the EMI calculator section
	    When navigating to the EMI calculator section
	    
	    # Use the sliders in the EMI calculator to set various values
	    And using the EMI calculator sliders
	    
	    # Verify the output values of the EMI calculator sliders
	    Then checking the EMI calculator slider output
	    
	    # Navigate to the loan tenure calculator section
	    When navigating to the loan tenure calculator
	    
	    # Use the sliders in the loan tenure calculator to set various values
	    And using the loan tenure calculator slider
	    
	    # Verify the output values of the loan tenure calculator sliders
	    Then checking the loan tenure calculator slider value
	    
	    # Close the browser after completing the tests
	    And closing the browser
