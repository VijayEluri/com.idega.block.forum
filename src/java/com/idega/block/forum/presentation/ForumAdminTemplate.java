package com.idega.block.forum.presentation;

import com.idega.block.forum.business.*;
import com.idega.jmodule.object.*;
import com.idega.jmodule.object.interfaceobject.*;
import java.sql.*;


/**
 * Title:        idegaForms
 * Description:
 * Copyright:    Copyright (c) 2000 idega margmi�lun hf.
 * Company:      idega margmi�lun hf.
 * @author idega 2000 - idega team - <a href="mailto:gummi@idega.is">Gu�mundur �g�st S�mundsson</a>
 * @version 1.0
 */

public class ForumAdminTemplate extends ForumAdminPresentation {


  public ForumAdminTemplate() throws Exception{
    super();
  }


  public ModuleObject ForumEdit_Presentation() throws SQLException{
    ForumList FList = getForumList();

    Table Frame = new Table(1,7);
      Frame.add(FList.getForumNameInput(),1,3);
      Frame.add(FList.getForumDescriptionArea(),1,5);
      Frame.add(FList.getForumSubmitButton(),1,7);

    return Frame;
  }


  public ModuleObject ForumEmail_Presentation() throws SQLException{
    ForumList FList = getForumList();

    Table Frame = new Table(1,5);
      Frame.add(FList.getEmailInput(),1,3);
      Frame.add(FList.getForumSubmitButton(),1,5);

    return Frame;
  }



} // Class ForumAdminTemplate
