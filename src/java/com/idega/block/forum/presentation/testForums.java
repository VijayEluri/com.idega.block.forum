package com.idega.block.forum.presentation;

import com.idega.jmodule.object.*;
import com.idega.jmodule.object.textObject.*;
import com.idega.block.forum.business.*;
import com.idega.block.forum.data.*;
import com.idega.jmodule.object.interfaceobject.*;
import com.idega.util.*;
import java.util.ResourceBundle.*;
import java.sql.*;
import java.lang.*;

/**
 * Title:        JForums<p>
 * Description:  <p>
 * Copyright:    Copyright (c) idega margmi�lun hf.<p>
 * Company:      idega margmi�lun hf.<p>
 * @author idega 2000 - idega team - <a href="mailto:gummi@idega.is">gummi@idega.is</a>
 * @version 1.0
 */



public class testForums extends ForumPresentation{

  private String[] Is = {"Umr��uflokkar","�r��ir","Uppf�rt s��ast","Fyrirs�gn","Skr� �r��","Efni ","H�fundur ","Sv�r","Sent","Nafn","Senda"};
  private String[] En = {"Forums","Threads","Last Updated","Headline","New Thread","Subject","Author","Answers","Sent","Name","Send"};
  //                        0         1           2             3           4         5           6           7      8      9     10
  private String[] Lang ;
  private String languageDir;
  // Colors of Module
  private String MenuColor ;
  private String ItemColor  ;
  private String MenuFontColor ;
  private String ItemFontColor ;
  private int fontSize ;
  private int headerFontSize;
  private java.util.Locale currentLocale;
  private java.util.ResourceBundle lang;

  //  Smi�ir

  public testForums() throws SQLException {
    super();
    setMenuColor("#4D6476");
    setItemColor("#C5C5C5");
    setItemMenuColor("#FFFFFF");
    setItemFontColor("#000000");
    setMainFontSize(2);
    setHeaderFontSize(2);

  }

  public testForums(String user_name, int user_id) throws SQLException {
    super(user_name, user_id);
    setMenuColor("#4D6476");
    setItemColor("#C5C5C5");
    setItemMenuColor("#FFFFFF");
    setItemFontColor("#000000");
    setMainFontSize(2);
    setHeaderFontSize(2);
  }

  public void setMenuColor(String MenuColor){
    this.MenuColor = MenuColor;
  }

  public void setItemColor(String ItemColor){
    this.ItemColor = ItemColor;
  }
  public void setItemMenuColor(String MenuFontColor){
    this.MenuFontColor = MenuFontColor;
  }

  public void setItemFontColor (String ItemFontColor){
    this.ItemFontColor = ItemFontColor;
  }

  public void setMainFontSize(int i){
    this.fontSize = i;
  }

  public void setHeaderFontSize(int i){
    this.headerFontSize = i;
  }
/*
  public void preset(){
    if(getSpokenLanguage().equalsIgnoreCase("En")){
     Lang = En;
     languageDir = "EN";
    }
    else if(getSpokenLanguage().equalsIgnoreCase("Is")){
     Lang = Is;
     languageDir = "IS";
    }
    else{
     Lang = Is;
     languageDir = "IS";
    }
  }
  */

  public void preset(){

    currentLocale = new java.util.Locale(getSpokenLanguage().toLowerCase(), "");
    java.util.ResourceBundle lang =
    java.util.ResourceBundle.getBundle("ForumBundle", currentLocale);
  }

  // F�ll

  public void initBooleans(){
    setUseForums(true);
    setUseUserRegistration(false);
    setUseLogin(true);
    setUseNameField(true);
  }

  // Make a list of Forums
  protected ModuleObject ForumList_Presentation(){
    // Edited by aron@idega.is 08.11.00
    Table Mainframe = new Table(1,2);
    Mainframe.setWidth("100%");
    // Header for the list of Forums "Header"
    Text ForumListHeader = new Text(lang.getString("forums"));
    ForumListHeader.setFontColor(MenuFontColor);
    ForumListHeader.setFontSize(headerFontSize);
    ForumListHeader.setFontStyle("Arial");
    ForumListHeader.setBold();
    // Header with "Threads"
    Text threadsHead = new Text(lang.getString("threads"));
    threadsHead.setFontColor(MenuFontColor);
    threadsHead.setFontSize(1);
    threadsHead.setBold();
    // Header With "Last Updated"
    Text threadUpdated = new Text(lang.getString("lastupdated"));
    threadUpdated.setFontColor(MenuFontColor);
    threadUpdated.setFontSize(1);
    threadUpdated.setBold();

    // Frame (1 row)around Header text, with color
    Table TH = new Table(4,1);
    TH.setColor(MenuColor);
    TH.setWidth("100%");
    TH.setWidth(2,"50");
    TH.setWidth(3,"8");
    TH.setWidth(4,"80");

    TH.setAlignment(2,1,"right");
    TH.setAlignment(4,1,"left");
    TH.add(ForumListHeader,1,1);
    TH.add(threadsHead, 2, 1);
    TH.add("",3,1);
    TH.add(threadUpdated, 4, 1);

    // Frame (1 row) around Header text + color frame, for padding
    Table THH = new Table(1,1);
    THH.setWidth("100%");
    THH.setCellpadding(1);
    THH.add(TH);
    Mainframe.add(THH,1,1);

    // Manipulate list of forums vith links
    for(int i = 0; i < List.length; i++){
      Link forumLink = new Link();
      Text forumTextDescr = new Text();
      Text NumberOfThreads = new Text();
      Text Lastupdated = new Text();
      ForumList theList = List[i];
      forumLink = theList.getForumLink();
      forumLink.setFontColor(MenuFontColor);
      forumLink.setFontStyle("Arial");
      forumLink.setFontSize(fontSize);

      Table nameUnit = new Table(4,1);
      nameUnit.setColor(MenuColor);
      nameUnit.setWidth("100%");
      //nameUnit.setWidth(1,"65%");
      nameUnit.setWidth(2,"50");
      nameUnit.setWidth(3,"10");
      nameUnit.setWidth(4,"80");
      nameUnit.setAlignment(2,1,"right");
      nameUnit.setAlignment(4,1,"left");
      nameUnit.add(forumLink, 1,1);
      nameUnit.add("",3,1);

      // Use later:
      //(theList.getNumberOfThreads(),2,1);
      //(theList.getNewThreadDate(),3,1);

      NumberOfThreads.setText(Integer.toString(theList.getNumberOfThreads()));
      NumberOfThreads.setFontSize(1);
      NumberOfThreads.setFontColor(MenuFontColor);
      NumberOfThreads.setFontStyle("Arial");

      Timestamp lastUpdate = theList.getNewThreadDate();
      if (lastUpdate != null){
        Lastupdated.setText(new idegaTimestamp(theList.getNewThreadDate()).toString(true, false, false));
        Lastupdated.setFontSize(1);
        Lastupdated.setFontColor(MenuFontColor);
        Lastupdated.setFontStyle("Arial");
      }

      nameUnit.add(NumberOfThreads,2,1);
      nameUnit.add(Lastupdated,4,1);

      forumTextDescr.setText(theList.getForumDescription());
      forumTextDescr.setFontStyle("Arial");
      forumTextDescr.setFontColor(ItemFontColor);
      forumTextDescr.setFontSize(fontSize);

      Table descrUnit = new Table(1,1);
      descrUnit.add(forumTextDescr,1,1);
      descrUnit.setWidth("100%");
      descrUnit.setColor(ItemColor);
      descrUnit.setColor(1,1,MenuFontColor);

      Table forumUnit = new Table(1,2);
      forumUnit.setWidth("100%");
      forumUnit.add(nameUnit,1,1);
      forumUnit.add(descrUnit,1,2);

      Mainframe.add(forumUnit,1,2);
    }
    return Mainframe;
  }

  // Still Unused
  protected ModuleObject SomeThreads_Presentation(){
    Table frame = new Table(1,3);
 /*   frame.setColor(1,2, "#99CC99");
    frame.setCellpadding(1);
    frame.setCellspacing(0);
    frame.setWidth(148);
    //frame.setBackgroundImage(1,1, new Image("/pics/templates/golfumrada.gif"));
    frame.add(Some, 1,2);
*/    return frame;
  }


  protected ModuleObject ThreadContents_Presentation() throws Exception{
    Table frame2 = new Table(1,1);
    frame2.setWidth("100%");
    frame2.setColor(MenuFontColor);

    for(int i = 0; i < Contents.length; i++){
    Table frame = null;
    ThreadContents thread = Contents[i];
    thread.setAnswerLinkObject(new Image(lang.getString("replyImage")));
    thread.setDeleteLinkObject(new Image(lang.getString("deleteImage")));

    if (thread.ReturnsLinks()){
      frame = new Table(1,3);
      frame.setRowAlignment(3, "right");
    }else{
      frame = new Table(1,2);
    }

    frame.setWidth("100%");


    Table headerTable = new Table(2, 2);
    //headerTable.setWidth(1,"100%");
    headerTable.setWidth(1,2,"50%");
    // Header with "Subject"
    Text subj = new Text(lang.getString("subject")+" :  ");
    subj.setFontColor(MenuFontColor);
    subj.setBold();
    subj.setFontSize(fontSize);
    headerTable.mergeCells(1,1,2,1);
    headerTable.add( subj, 1, 1);

    // Header with "Author"
    Text author = new Text(lang.getString("author")+" :  ");
    author.setFontColor(MenuFontColor);
    author.setBold();
    author.setFontSize(fontSize);
    headerTable.add(author, 1, 2);

    // Header with "Sent"
    Text sentDate = new Text(lang.getString("sent")+" :  ");
    sentDate.setFontColor(MenuFontColor);
    sentDate.setBold();
    sentDate.setFontSize(fontSize);
    headerTable.add(sentDate, 2, 2);

    // Get the subject
    Text subj2 = new Text(thread.getThreadSubject());
    subj2.setFontColor(MenuFontColor);
    subj2.setFontSize(fontSize);
    headerTable.add(subj2, 1, 1);

    // Get the Author
    Text author2 = new Text(thread.getUserName());
    author2.setFontColor(MenuFontColor);
    author2.setFontSize(fontSize);
    headerTable.add(author2, 1, 2);

    // Get the last updated date
    if (thread.getThreadDate() != null){
      Text date2 = new Text(new idegaTimestamp(thread.getThreadDate()).toString(true, false, false));
      date2.setFontColor(MenuFontColor);
      date2.setFontSize(fontSize);
      headerTable.add(date2, 2, 2);
    }

    headerTable.setColor(MenuColor);
    headerTable.setWidth("100%");
    headerTable.setCellspacing(2);

    // old//    frame.add( Contents.get , 1, 1);
    frame.setRowColor(2, ItemColor);

    // table for textbody of thread
    Table Textbody = new Table();
    Textbody.setCellpadding(5);
    Textbody.setWidth("100%");
    Textbody.setColor(ItemColor);
    Textbody.setColor(1,1,MenuFontColor);
    // get thread-body
    Text bodyText = new Text(thread.getThreadBody());
    bodyText.setFontColor(ItemFontColor) ;
    bodyText.setFontSize(fontSize);
    Textbody.add(bodyText);

    frame.add(Textbody, 1, 2);
    // get a delete link
    Link delTemp = thread.getDeleteLink();
    if(delTemp != null){
      if(isAdministrator(getModuleInfo())){
       frame.add(delTemp,1,3);
       frame.add("   ",1,3);
      }
    }
    // get anser link
    Link ansTemp = thread.getAnswerLink();
    if (ansTemp != null){
      frame.add(thread.getAnswerLink(),1,3);
    }

    frame.add(headerTable, 1, 1);
    frame2.add(frame);
    }

    return frame2;
  }


  protected ModuleObject ThreadEntry_Presentation(boolean useNameTextInput){
    // Button text "Send"
    Entry.setSubjectTextInput(false);
    Entry.setBodyTextArea();
    Entry.setSubmitButton(lang.getString("send"));

    Table frame = new Table(1,3);
    frame.setCellspacing(0);
    frame.setCellpadding(0);

    frame.add(Text.emptyString(),1,1);
    frame.setHeight(1,"2");

    // Header table for Thread Entry
    frame.setHeight(2,"25");
    // Header with "new thread"
    Text newThread = new Text(lang.getString("newthread"));
    newThread.setFontColor(MenuFontColor);
    newThread.setFontSize(fontSize);
    newThread.setBold();

    Table header = new Table(2,1);
    header.setWidth(1, "9");
    header.setWidth("100%");
    header.add(newThread, 2, 1);

    frame.add(header, 1, 2);



    //frame.setBackgroundImage( 1, 1, new Image("/pics/forum/spjallbanner.gif"));
    frame.setWidth("450");

    Table toolTable = new Table(3,6);
    toolTable.setAlignment("center");
    toolTable.setCellspacing(0);
    toolTable.setCellpadding(0);
    toolTable.setWidth(1,"20");
    toolTable.setWidth("20");
    toolTable.setWidth(3,"20");

    toolTable.add(Text.emptyString(), 1,1);
    toolTable.add(Text.emptyString(), 2,1);
    toolTable.add(Text.emptyString(), 3,1);
    toolTable.setHeight(1,"5");

    toolTable.add(Text.emptyString(), 1,2);
    toolTable.add(Text.emptyString(), 2,2);
    toolTable.add(Text.emptyString(), 3,2);
    toolTable.setHeight(2,"10");

    toolTable.setHeight(3,"20");
    toolTable.setHeight(4,"25");
    toolTable.setHeight(5,"20");
    toolTable.setHeight(6,"20");
    toolTable.setRowVerticalAlignment(6, "middle");
    // Header with "Headline"
    Text subject = new Text(lang.getString("headline"));
    subject.setFontColor(MenuColor);
    subject.setFontSize("2");
    subject.setFontStyle("Arial");
    subject.setBold();

    toolTable.add(subject, 2, 2);
    TextInput subjInput = Entry.getSubjectTextInput();
    subjInput.setSize(50);
    subjInput.setMaxlength(50);

    toolTable.add(subjInput, 2, 3);
    // Header with "Subject"
    Text body = new Text(lang.getString("subject"));
    body.setFontColor(MenuColor);
    body.setFontSize("2");
    body.setFontStyle("Arial");
    body.setBold();

    toolTable.add(body, 2, 4);
    TextArea bodyText = Entry.getBodyTextArea();
    bodyText.setHeight(14);
    bodyText.setWidth(50);
    toolTable.add(bodyText, 2, 5);

    Table NameSend = new Table(2,2);
    // Check if signature writing is allowed
    if (useNameTextInput){
      //Table nameTable = new Table(1,2);
      NameSend.setCellpadding(0);
      NameSend.setCellspacing(0);

      TextInput nameInput = Entry.getNameTextInput();
      nameInput.setMaxlength(10);
      // Header with "Name"
      Text name = new Text(lang.getString("name"));
      name.setFontColor(MenuColor);
      name.setFontSize(fontSize);
      name.setFontStyle("Arial");
      name.setBold();

      NameSend.add(name,1,1);
      NameSend.add(nameInput,1,2);

      //toolTable.add(NameSend,2,5);
    }

    //
    Table submit = new Table(2,1);
    submit.setCellspacing(0);
    submit.setCellpadding(0);
    submit.setWidth(1,"300");
    submit.add(Entry.getSubmitButton(), 2, 1);
    submit.setAlignment(2,1,"right");

    NameSend.add(submit, 2, 2);
    //NameSend.setAlignment(3,2,"right");

    //toolTable.add(submit, 2, 6);
    //toolTable.setColor(ItemColor);
    toolTable.add(NameSend,2,5);
    toolTable.setColor(ItemColor);

    frame.add(toolTable, 1, 3);
    frame.setRowColor(2, MenuColor);
    frame.setRowColor(3, ItemColor);
    frame.setAlignment("center");

//    toolTable.setBorder(1);
//    frame.setBorder(1);

    return frame;
  }


  protected ModuleObject ThreadTree_Presentation(){

    if (firstTree){
      Tree.setTempIcon(new Image(lang.getString("upsImage")));
      Tree.setOpenCloseLink(new Image(lang.getString("openImage")), new Image(lang.getString("closeImage")));
      Tree.setNewThreadLink(new Image(lang.getString("newImage")));
      Tree.setToForumListLink(new Image(lang.getString("forumImage")));
    }

    Table frame = new Table(1,4);
    frame.setWidth("100%");
    frame.add(Tree.getOpenCloseLink(), 1, 1);
    frame.addText("   ", 1, 1);
    frame.add(Tree.getNewThreadLink(), 1, 1);
    if (getUseForums()){
       frame.addText("   ", 1, 1);
       Link toForums = Tree.getToForumListLink();
       frame.add(toForums, 1, 1);
    }

    frame.add(Text.emptyString(), 1, 2);
    frame.setHeight(2, "10");

    // Label Tafla byrjar
    Table treeLabel = new Table(5,1);
    treeLabel.add(Text.emptyString(), 1,1);
    treeLabel.add(Text.emptyString(), 2,1);
    treeLabel.add(Text.emptyString(), 3,1);
    treeLabel.add(Text.emptyString(), 4,1);
    treeLabel.add(Text.emptyString(), 5,1);
    treeLabel.setHeight("10");
    treeLabel.setWidth("100%");
    treeLabel.setColor(MenuColor);

    Text subj = new Text("<small>"+lang.getString("subject"));
    subj.setFontColor(MenuFontColor);
    treeLabel.add( subj, 2, 1);

    Text author = new Text("<small>"+lang.getString("author"));
    author.setFontColor(MenuFontColor);
    treeLabel.add(author, 3, 1);

    Text ans = new Text("<small>"+lang.getString("threads"));
    ans.setFontColor(MenuFontColor);
    treeLabel.add(ans, 4, 1);

    Text date = new Text("<small>"+lang.getString("lastupdated"));
    date.setFontColor(MenuFontColor);
    treeLabel.add(date, 5, 1);

    treeLabel.setWidth(1, "20");
    treeLabel.setWidth("100%");
    treeLabel.setWidth(3, "53");
    treeLabel.setWidth(4, "30");
    treeLabel.setWidth(5, "72");

    treeLabel.setAlignment(2,1, "left");
    treeLabel.setAlignment(3,1, "center");
    treeLabel.setAlignment(4,1, "center");
    treeLabel.setAlignment(5,1, "center");

    frame.add(treeLabel, 1, 2);
    //Labeltafla komin inn.

    frame.add(Tree, 1, 3);
    Link LastPage = (Link)Tree.getLastPageLink();
    Link NextPage = (Link)Tree.getNextPageLink();

    LastPage.setObject(new Image(lang.getString("previousImage")));
    NextPage.setObject(new Image(lang.getString("nextImage")));

    frame.add(LastPage,1,4);
    frame.addText("   ", 1,4);
    frame.add(NextPage,1,4);
    return frame;
  }


  protected ModuleObject ForumError_Presentation( String errorType ){
    return new Table();
  }


  protected ModuleObject ForumAdministration_Presentation(){
    return new Table();
  }


  protected ModuleObject UserRegistration_Presentation(){
    return new Table();
  }


  protected void initSomeThreads() throws SQLException {
     Some = new SomeThreads("/forum/index.jsp", 3, true);
    //Some.setBackgroundColor(MenuFontColor);
    //Some.setLinkCellWidth("146");
    //Some.setLinkCellHeight("22");

  }

  protected ModuleObject ForumNameHeader(){
    Text forumName= new Text(getCurrentForumName());
    forumName.setFontColor(MenuFontColor);
    forumName.setFontSize(headerFontSize);
    forumName.setFontStyle("Arial");
    forumName.setBold();
    Table T = new Table(1,1);
    T.setWidth("100%");
    T.setColor(MenuColor);
    T.add(forumName,1,1);
    Table TT = new Table(1,1);
    TT.setWidth("100%");
    TT.setCellpadding(3);
    TT.add(T,1,1);

    return TT;
  }




} // Class GrayForums