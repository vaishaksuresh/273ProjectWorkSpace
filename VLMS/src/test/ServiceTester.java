/**
 * 
 */
package test;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.vlms.sjsu.entity.*;
import com.vlms.sjsu.service.Service;
import com.vlms.sjsu.util.AppUtils;

/**
 * @author vaishaksuresh
 * 
 */
public class ServiceTester {

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#createUpdateUser(com.vlms.sjsu.entity.User)}
	 * .
	 */
	@Test
	public void testCreateUpdateUser() {
		Service objService = new Service();
		User objUser = new User();
		objUser.setUserId(100041118);
		objUser.setEmail("vaishak.suresha@gmail.com");
		objUser.setPassword("f4a467065a1cea392bc7a5a65e73c3d3");
		objUser.setAddress("Address");
		
//		String strResult = objService.createUpdateUser(objUser);
//		System.out.println(strResult);
//		Assert.assertFalse(strResult != "");
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#signIn(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testSignIn() {

		Service objService = new Service();

		User[] arrUser = objService.signIn("vaishak.suresh@gmail.com",
				AppUtils.md5("anupama"));
		Assert.assertFalse(arrUser[0].getUserId() == 0);
	}
	/**
	 * Test method for {@link com.vlms.sjsu.service.Service#listAllMovies(int)}.
	 */
	@Test
	public void testListAllMovies() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#findUser(com.vlms.sjsu.entity.User)}
	 * .
	 */
	@Test
	public void testFindUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#findMovie(com.vlms.sjsu.entity.Movie)}
	 * .
	 */
	@Test
	public void testFindMovie() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#generateBill(java.lang.String)}.
	 */
	@Test
	 public void testGenerateBill() {
        String id = "1";
        Service objService = new Service();
        Bill[] user = objService.generateBill(id);
        if (user.length == 0)
            Assert.fail("fAILED::");
        else
            Assert.assertTrue(true);
    }

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#createMovie(com.vlms.sjsu.entity.Movie)}
	 * .
	 */
	@Test
	public void testCreateMovie() {
		//fail("Not yet implemented");

		Service objService = new Service();

		Movie objMovie = new Movie();
		objMovie.setMovieName("rhtdm");
		objMovie.setMovieBanner("Hello Productions");
		objMovie.setCategory("Bollywood");
		objMovie.setReleaseDate("2012-11-16");
		objMovie.setAvailableCopies(10);
		objMovie.setRentAmount((float)40);

		String result = objService.createMovie(objMovie);

		Assert.assertFalse(result.toLowerCase().indexOf("true")<0);
		Assert.assertTrue(result.toLowerCase().indexOf("true")>-1);

	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#updateMovie(com.vlms.sjsu.entity.Movie, com.vlms.sjsu.entity.Movie)}
	 * .
	 */
	@Test
	public void testUpdateMovie() {
		//fail("Not yet implemented");
		//fail("Not yet implemented");

		Service objService = new Service();

		Movie objMovie = new Movie();
		objMovie.setMovieId(5);
		objMovie.setMovieName("rhtdm");
		objMovie.setMovieBanner("Hello Productions");
		objMovie.setCategory("Comedy");
		objMovie.setReleaseDate("2012-11-16");
		objMovie.setAvailableCopies(20);
		objMovie.setRentAmount((float)40);

		String result = objService.updateMovie(objMovie);

		Assert.assertFalse(result.toLowerCase().indexOf("true")<0);
		Assert.assertTrue(result.toLowerCase().indexOf("true")>-1);


	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#deleteMovie(java.lang.String)}.
	 */
	@Test
	public void testDeleteMovie() {
		//fail("Not yet implemented");
		Service objService = new Service();
		String movieId = "1";
		String result =  objService.deleteMovie(movieId);
		Assert.assertFalse(result.toLowerCase().indexOf("true")<0);
		Assert.assertTrue(result.toLowerCase().indexOf("true")>-1);
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#issueMovie(java.lang.String, com.vlms.sjsu.entity.User)}
	 * .
	 */
	@Test
	public void testIssueMovie() {
		//fail("Not yet implemented");
		Service objService = new Service();
		String movieId = "1";
		User user = new User();
		user.setUserId(100000001);
		user.setMemberType("Simple");
		String result = objService.issueMovie(movieId, user);
		Assert.assertFalse(result.toLowerCase().indexOf("true")<0);
		Assert.assertTrue(result.toLowerCase().indexOf("true")>-1);
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#getUserDetails(java.lang.Integer)}.
	 */
	@Test
	public void testGetUserDetails() {
		Integer userID = new Integer(2);
		User userObj = new User();
		Service objService = new Service();
		userObj = objService.getUserDetails(userID);
		if(userObj.getUserId()==0)
			Assert.fail("User doesn't exist");
		else
			Assert.assertNotNull(userObj);
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#getMovieDetails(java.lang.Integer)}.
	 */
	@Test
	public void testGetMovieDetails() {
		Integer strMovieId = new Integer(2);
		Movie movieset = new Movie();
		Service objService = new Service();
		movieset= objService.getMovieDetails(strMovieId);
		if(movieset.getMovieId()==0)
				Assert.fail("Movie doesnot exist");
		else
			Assert.assertNotNull(movieset);

	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#returnMovie(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testReturnMovie() {
		//Integer strMovieId=new Integer(2);
		//Integer strUserId=new Integer(2);
		int strMovieId=2;
		int strUserId=2;
		
		Service objService = new Service();
        String result = objService.returnMovie(strMovieId, strUserId);
        Assert.assertTrue(result.indexOf("success") > -1);
        Assert.assertFalse(result.indexOf("success") < -1);
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#listAllUsers(java.lang.String)}.
	 */
	@Test
	public void testListAllUsers() {
        String noOfRows = "4";
        Service objService = new Service();
        User[] user = objService.listAllUsers(noOfRows);
        if (user.length == 0)
            Assert.fail("Test failed ::");
        else
            Assert.assertTrue(true);
        // Assert.assertFalse(arrUser[0].getUserId() == 0);
    }

    /**
     * Test method for
     * {@link com.vlms.sjsu.service.Service#deleteUser(java.lang.String)}.
     */
    @Test
    public void testDeleteUser() {
        String userID = "5";
        Service objService = new Service();
        String result = objService.deleteUser(userID);
        Assert.assertTrue(result.indexOf("Success") > -1);
        Assert.assertFalse(result.indexOf("Success") < -1);
    }

}
