package fi.restservice.database;
import java.sql.Types;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fi.restservice.database.MobileDAOService;
import fi.restservice.model.Mobile;

@Repository
public class MobileDAOImplService implements MobileDAOService {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void postMobile(final Mobile mobile) {	
		final String sql = "INSERT INTO mobile (person_id, "
				+ "mobile_manufacturer, "
				+ "mobile_model, "
				+ "mobile_specs,"
				+ "mobile_freedescription, price) values(?,?,?,?,?,?)";
		System.out.println("public void postMobile(final Mobile mobile) " +mobile.toString());
		Object[] parameters = new Object[] { mobile.getPersonId(),
										mobile.getMobileManufacturer(),
										mobile.getMobileModel(),
										mobile.getMobileSpecs(),
										mobile.getMobileFreedescription(),
										mobile.getPrice()};
		jdbcTemplate.update(sql, parameters);
	}

	public Mobile getMobile(int id) {
		final String sql = "select row_id, person_id, "
				+ "mobile_manufacturer, "
				+ "mobile_model, "
				+ "mobile_specs,"
				+ "mobile_freedescription,"
				+ "price from mobile where row_id = ?";
		Object[] parameters = new Object[] { id };
		RowMapper<Mobile> mapper = new MobileRowMapper();

		Mobile mobile;
		try {
			mobile = jdbcTemplate.queryForObject(sql, parameters, mapper);
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new MobileNotFoundExceptions(e);
		}
		return mobile;
	}
	
	public List<Mobile> getMobile() {
		final String sql = "select row_id, "
				+ "person_id, "
				+ "mobile_manufacturer, "
				+ "mobile_model, "
				+ "mobile_specs,"
				+ "mobile_freedescription,"
				+ "price from mobile";
		RowMapper<Mobile> mapper = new MobileRowMapper();
		List<Mobile> mobiles = jdbcTemplate.query(sql, mapper);

		return mobiles;
	}
	
	public List<Mobile> getOwnMobile(int personId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Mobile> getPersonMobile(int id) {
		final String sql = "select row_id, person_id, "
				+ "mobile_manufacturer, "
				+ "mobile_model, "
				+ "mobile_specs,"
				+ "mobile_freedescription,"
				+ "price from mobile where person_id = ?";
		
		Object[] parameters = new Object[] { id };
		RowMapper<Mobile> mapper = new MobileRowMapper();

		List <Mobile> mobile;
		try {
			mobile = jdbcTemplate.query(sql, mapper, parameters);
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new MobileNotFoundExceptions(e);
		}
		System.out.println(mobile.toString());
		return mobile;

	}

	public void putMobile(Mobile mobile) {
		final String sql = "UPDATE mobile set mobile_manufacturer=?,"
				+ "mobile_model=?,"
				+ "mobile_specs=?,"
				+ "mobile_freedescription=?,"
				+ "price=? "
				+ "where row_id = ?";
		Object[] parameters = new Object[] {
				mobile.getMobileManufacturer(),
				mobile.getMobileModel(),
				mobile.getMobileSpecs(),
				mobile.getMobileFreedescription(),
				mobile.getPrice(),
				mobile.getRowId()
		};
		int[] types = {
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.DOUBLE,
				Types.INTEGER
		};
		jdbcTemplate.update(sql, parameters, types);
		
	}

	public void deleteMobile(Mobile mobile) {
		jdbcTemplate.update("delete from mobile where row_id = ?", new Object[] {mobile.getRowId()});
	}
}
