package org.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import files.MockPayloads;
import io.restassured.path.json.JsonPath;

public class MockApiTestExample {
	private JsonPath js;
	private int count = 0;
	private int purchaseAmount = 0;
	private int sum = 0;

	@BeforeClass
	public void init() {
		js = new JsonPath(MockPayloads.mockCourse());
	}

	@Test
	public void printNumberOfCourse() {

		/**
		 * Print No of courses returned by API
		 */
		count = js.getInt("courses.size()");
		System.out.println("No of courses returned by API : " + count);
	}

	@Test
	public void printPurchaseAmount() {
		/**
		 * Print Purchase Amount
		 */
		purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase amount : " + purchaseAmount);
	}

	@Test
	public void printTitleOfTheFirstCourse() {
		/**
		 * Print Title of the first course
		 */

		System.out.println("Title of the first course : " + js.getString("courses[0].title"));
	}

	@Test
	public void printCourseTitleAndPrice() {
		/**
		 * Print All course titles and their respective Prices
		 */

		for (int i = 0; i < count; i++) {
			System.out.println("All course titles : " + js.getString("courses[" + i + "].title"));
			System.out.println("All course price : " + js.getString("courses[" + i + "].price"));
		}
	}

	@Test
	public void noofCopiesSoldByRPA() {
		/**
		 * Print no of copies sold by RPA Course
		 */
		for (int i = 0; i < count; i++) {
			String courseTitles = js.get("courses[" + i + "].title").toString();
			if (courseTitles.equalsIgnoreCase("RPA")) {
				System.out.println("RPA course price : " + js.getString("courses[" + i + "].price"));
				System.out.println("RPA sold copy : " + js.getInt("courses[" + i + "].copies"));
				break;
			}

		}
	}

	@Test
	public void validatePurchasePrice() {
		/**
		 * Verify if Sum of all Course prices matches with Purchase Amount
		 */
		for (int i = 0; i < count; i++) {
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");

			int amount = price * copies;
			sum = sum + amount;
		}
		Assert.assertEquals(sum, purchaseAmount);
	}

}
