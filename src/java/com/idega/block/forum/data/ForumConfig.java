package com.idega.block.forum.data;

import com.idega.data.*;
import java.sql.*;

/**
 * Title:        JForums<p>
 * Description:  <p>
 * Copyright:    Copyright (c) idega margmišlun hf.<p>
 * Company:      idega margmišlun hf.<p>
 * @author idega 2000 - idega team - <a href="mailto:gummi@idega.is">gummi@idega.is</a>
 * @version 1.0
 */

public class ForumConfig extends GenericEntity {

  public ForumConfig() {
    super();
  }


  public ForumConfig(int id) throws SQLException{
    super(id);
  }

  public void initializeAttributes(){
    addAttribute(getIDColumnName());
  }

  public String getIDColumnName(){
    return "forum_config_id";
  }


  public String getEntityName(){
    return "i_forum_config";
  }




}   // class ForumConfig
