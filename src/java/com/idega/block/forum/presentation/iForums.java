package com.idega.block.forum.presentation;

import com.idega.jmodule.object.ModuleObject;
import java.lang.*;
import java.sql.*;


/**
 * Title:        idegaForms
 * Description:
 * Copyright:    Copyright (c) 2000 idega margmišlun hf.
 * Company:      idega margmišlun hf.
 * @author idega 2000 - idega team - <a href="mailto:gummi@idega.is">gummi@idega.is</a>
 * @version 1.0
 */

public class iForums extends GrayForums {


  public iForums() throws SQLException {
    super();
    setMenuColor("#EA990C");
    setItemColor("#C5C5C5");
  }


  public iForums(String user_name, int user_id) throws SQLException {
    super(user_name, user_id);
    setMenuColor("#EA990C");
    setItemColor("#C5C5C5");
   }


  public void initBooleans(){
    setUseForums(true);
    setUseUserRegistration(false);
    setUseLogin(true);
    setUseNameField(true);
  }





 } // Class iForums