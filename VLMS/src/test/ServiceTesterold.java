/**
 * 
 */
package test;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.vlms.sjsu.entity.User;
import com.vlms.sjsu.service.Service;
import com.vlms.sjsu.util.AppUtils;

/**
 * @author vaishaksuresh
 * 
 */
public class ServiceTesterold {

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
		
		String strResult = objService.createUpdateUser(objUser);
		System.out.println(strResult);
		//Assert.assertFalse(arrUser[0].getUserId() == 0);
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
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#createMovie(com.vlms.sjsu.entity.Movie)}
	 * .
	 */
	@Test
	public void testCreateMovie() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#updateMovie(com.vlms.sjsu.entity.Movie, com.vlms.sjsu.entity.Movie)}
	 * .
	 */
	@Test
	public void testUpdateMovie() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#deleteMovie(java.lang.String)}.
	 */
	@Test
	public void testDeleteMovie() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#issueMovie(java.lang.String, com.vlms.sjsu.entity.User)}
	 * .
	 */
	@Test
	public void testIssueMovie() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#getUserDetails(java.lang.Integer)}.
	 */
	@Test
	public void testGetUserDetails() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#getMovieDetails(java.lang.Integer)}.
	 */
	@Test
	public void testGetMovieDetails() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#returnMovie(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testReturnMovie() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#listAllUsers(java.lang.String)}.
	 */
	@Test
	public void testListAllUsers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.vlms.sjsu.service.Service#deleteUser(java.lang.String)}.
	 */
	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

}
