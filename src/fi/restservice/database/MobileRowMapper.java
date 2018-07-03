package fi.restservice.database;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import fi.restservice.model.Mobile;
import fi.restservice.model.MobileImpl;

public class MobileRowMapper implements RowMapper<Mobile> {
/**


  photo mediumblob,
 */
	public Mobile mapRow(ResultSet rs, int rowNum) throws SQLException {
		Mobile mobile = new MobileImpl();
		
		mobile.setRowId(rs.getInt("row_id"));
		mobile.setPersonId(rs.getInt("person_id"));
		mobile.setMobileManufacturer((rs.getString("mobile_manufacturer")));
		mobile.setMobileModel(rs.getString("mobile_model"));
		mobile.setMobileSpecs(rs.getString("mobile_specs"));
		mobile.setMobileFreedescription(rs.getString("mobile_freedescription"));
		mobile.setPrice(rs.getDouble("price"));
		//mobile.setPhoto(rs.getBlob("photo"));
		return mobile;
	}

}
