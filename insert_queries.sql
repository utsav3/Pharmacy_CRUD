INSERT INTO doctor(ssn, name,specialty,yearsOfExperience) VALUES ("888888","Dr.Riha","Ortho",7);
INSERT INTO doctor(ssn, name,specialty,yearsOfExperience) VALUES ("666666","Dr.Tony","Psychologist",9);
INSERT INTO doctor(ssn, name,specialty,yearsOfExperience) VALUES ("111111","Dr.Tammy","Gyno",3);
INSERT INTO doctor(ssn, name,specialty,yearsOfExperience) VALUES ("777777","Dr.Abby","Gyno",3);
INSERT INTO doctor(ssn, name,specialty,yearsOfExperience) VALUES ("222222","Dr.Ashley","Physician",2);
INSERT INTO doctor(ssn, name,specialty,yearsOfExperience) VALUES ("333333","Dr.Tom","Ortho",5);
INSERT INTO doctor(ssn, name,specialty,yearsOfExperience) VALUES ("444444","Dr.Bob","Gyno",9);
INSERT INTO doctor(ssn, name,specialty,yearsOfExperience) VALUES ("555555","Dr.Tammy","Psychologist",3);
INSERT INTO doctor(ssn, name,specialty,yearsOfExperience) VALUES ("999999","Dr.Mark","Physician",3);
INSERT INTO doctor(ssn, name,specialty,yearsOfExperience) VALUES ("111222","Dr.Chandler","Gyno",6);


INSERT INTO pharmacy(pharm_id,name,address,phone) VALUES ("1","pharmacy1","Carbondale",222222);
INSERT INTO pharmacy(pharm_id,name,address,phone) VALUES ("2","pharmacy2","Chicago",1111111);
INSERT INTO pharmacy(pharm_id,name,address,phone) VALUES ("3","pharmacy3","NYC",3333333);
INSERT INTO pharmacy(pharm_id,name,address,phone) VALUES ("4","pharmacy4","LA",4444444);
INSERT INTO pharmacy(pharm_id,name,address,phone) VALUES ("5","pharmacy5","SAF",5555555);
INSERT INTO pharmacy(pharm_id,name,address,phone) VALUES ("6","pharmacy6","San Diego",6666666);
INSERT INTO pharmacy(pharm_id,name,address,phone) VALUES ("7","pharmacy7","Boston",7777777);
INSERT INTO pharmacy(pharm_id,name,address,phone) VALUES ("8","pharmacy8","DC",8888888);
INSERT INTO pharmacy(pharm_id,name,address,phone) VALUES ("9","pharmacy9","Memphis",9999999);
INSERT INTO pharmacy(pharm_id,name,address,phone) VALUES ("10","pharmacy10","Carbondale",1112222);



INSERT INTO pharm_co(name,phone) VALUES ("Company1", 1111111111);
INSERT INTO pharm_co(name,phone) VALUES ("Company2", 222222222);
INSERT INTO pharm_co(name,phone) VALUES ("Company3", 333333333);
INSERT INTO pharm_co(name,phone) VALUES ("Company4", 444444444);
INSERT INTO pharm_co(name,phone) VALUES ("Company5", 555555555);
INSERT INTO pharm_co(name,phone) VALUES ("Company6", 666666666);
INSERT INTO pharm_co(name,phone) VALUES ("Company7", 777777777);
INSERT INTO pharm_co(name,phone) VALUES ("Company8", 888888888);
INSERT INTO pharm_co(name,phone) VALUES ("Company9", 999999999);
INSERT INTO pharm_co(name,phone) VALUES ("Company10", 1111122222);





INSERT INTO pri_phy_patient(ssn,name,birth_date,address,phy_ssn) VALUES (111222,"Ryan","1994-07-14","New York",(SELECT ssn FROM doctor WHERE ssn="111111"));
INSERT INTO pri_phy_patient(ssn,name,birth_date,address,phy_ssn) VALUES (999999,"Ella","1995-09-14","Chicago",(SELECT ssn FROM doctor WHERE ssn="333333"));
INSERT INTO pri_phy_patient(ssn,name,birth_date,address,phy_ssn) VALUES (888888,"Ella","1995-09-14","Chicago",(SELECT ssn FROM doctor WHERE ssn="444444"));
INSERT INTO pri_phy_patient(ssn,name,birth_date,address,phy_ssn) VALUES (777777,"Ella","1995-09-14","Chicago",(SELECT ssn FROM doctor WHERE ssn="555555"));
INSERT INTO pri_phy_patient(ssn,name,birth_date,address,phy_ssn) VALUES (666666,"Ella","1995-09-14","Chicago",(SELECT ssn FROM doctor WHERE ssn="666666"));
INSERT INTO pri_phy_patient(ssn,name,birth_date,address,phy_ssn) VALUES (555555,"Ella","1995-09-14","Chicago",(SELECT ssn FROM doctor WHERE ssn="777777"));
INSERT INTO pri_phy_patient(ssn,name,birth_date,address,phy_ssn) VALUES (444444,"Ella","1995-09-14","Chicago",(SELECT ssn FROM doctor WHERE ssn="888888"));
INSERT INTO pri_phy_patient(ssn,name,birth_date,address,phy_ssn) VALUES (333333,"Ella","1995-09-14","Chicago",(SELECT ssn FROM doctor WHERE ssn="999999"));
INSERT INTO pri_phy_patient(ssn,name,birth_date,address,phy_ssn) VALUES (222222,"Ella","1995-09-14","Chicago",(SELECT ssn FROM doctor WHERE ssn="222222"));
INSERT INTO pri_phy_patient(ssn,name,birth_date,address,phy_ssn) VALUES (111111,"Ella","1995-09-14","Chicago",(SELECT ssn FROM doctor WHERE ssn="111222"));




INSERT INTO make_drug (trade_name,pharm_co_name,formula) VALUES("drug1",(SELECT name FROM pharm_co WHERE name="Company1"),"C12H22OH");
INSERT INTO make_drug (trade_name,pharm_co_name,formula) VALUES("drug2",(SELECT name FROM pharm_co WHERE name="Company2"),"C12H22OH");
INSERT INTO make_drug (trade_name,pharm_co_name,formula) VALUES("drug3",(SELECT name FROM pharm_co WHERE name="Company3"),"C12H22OH");
INSERT INTO make_drug (trade_name,pharm_co_name,formula) VALUES("drug4",(SELECT name FROM pharm_co WHERE name="Company4"),"C12H22OH");
INSERT INTO make_drug (trade_name,pharm_co_name,formula) VALUES("drug5",(SELECT name FROM pharm_co WHERE name="Company5"),"C12H22OH");
INSERT INTO make_drug (trade_name,pharm_co_name,formula) VALUES("drug6",(SELECT name FROM pharm_co WHERE name="Company6"),"C12H22OH");
INSERT INTO make_drug (trade_name,pharm_co_name,formula) VALUES("drug7",(SELECT name FROM pharm_co WHERE name="Company7"),"C12H22OH");
INSERT INTO make_drug (trade_name,pharm_co_name,formula) VALUES("drug8",(SELECT name FROM pharm_co WHERE name="Company8"),"C12H22OH");
INSERT INTO make_drug (trade_name,pharm_co_name,formula) VALUES("drug9",(SELECT name FROM pharm_co WHERE name="Company9"),"C12H22OH");
INSERT INTO make_drug (trade_name,pharm_co_name,formula) VALUES("drug10",(SELECT name FROM pharm_co WHERE name="Company10"),"C12H22OH");





INSERT INTO Prescription(pre_id,status,ssn,phy_ssn,pre_date,quantity,trade_name,pharm_co_name) 
VALUES (1,"ready-to-go",(SELECT ssn FROM pri_phy_patient WHERE ssn="111222"),(SELECT ssn FROM doctor WHERE ssn="111111"),"1994-06-07",1,(SELECT trade_name FROM make_drug WHERE trade_name="drug1"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company1"));
INSERT INTO Prescription(pre_id,status,ssn,phy_ssn,pre_date,quantity,trade_name,pharm_co_name) 
VALUES (2,"completed",(SELECT ssn FROM pri_phy_patient WHERE ssn="999999"),(SELECT ssn FROM doctor WHERE ssn="222222"),"1994-06-07",2,(SELECT trade_name FROM make_drug WHERE trade_name="drug2"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company2"));
INSERT INTO Prescription(pre_id,status,ssn,phy_ssn,pre_date,quantity,trade_name,pharm_co_name) 
VALUES (3,"yet-to-go",(SELECT ssn FROM pri_phy_patient WHERE ssn="888888"),(SELECT ssn FROM doctor WHERE ssn="333333"),"1994-06-07",3,(SELECT trade_name FROM make_drug WHERE trade_name="drug3"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company3"));
INSERT INTO Prescription(pre_id,status,ssn,phy_ssn,pre_date,quantity,trade_name,pharm_co_name) 
VALUES (4,"completed",(SELECT ssn FROM pri_phy_patient WHERE ssn="777777"),(SELECT ssn FROM doctor WHERE ssn="444444"),"1994-06-07",4,(SELECT trade_name FROM make_drug WHERE trade_name="drug4"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company4"));
INSERT INTO Prescription(pre_id,status,ssn,phy_ssn,pre_date,quantity,trade_name,pharm_co_name) 
VALUES (5,"ready-to-go",(SELECT ssn FROM pri_phy_patient WHERE ssn="666666"),(SELECT ssn FROM doctor WHERE ssn="555555"),"1994-06-07",5,(SELECT trade_name FROM make_drug WHERE trade_name="drug5"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company5"));
INSERT INTO Prescription(pre_id,status,ssn,phy_ssn,pre_date,quantity,trade_name,pharm_co_name) 
VALUES (6,"completed",(SELECT ssn FROM pri_phy_patient WHERE ssn="555555"),(SELECT ssn FROM doctor WHERE ssn="666666"),"1994-06-07",6,(SELECT trade_name FROM make_drug WHERE trade_name="drug6"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company6"));
INSERT INTO Prescription(pre_id,status,ssn,phy_ssn,pre_date,quantity,trade_name,pharm_co_name) 
VALUES (7,"yet-to-go",(SELECT ssn FROM pri_phy_patient WHERE ssn="444444"),(SELECT ssn FROM doctor WHERE ssn="777777"),"1994-06-07",7,(SELECT trade_name FROM make_drug WHERE trade_name="drug7"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company7"));
INSERT INTO Prescription(pre_id,status,ssn,phy_ssn,pre_date,quantity,trade_name,pharm_co_name) 
VALUES (8,"ready-to-go",(SELECT ssn FROM pri_phy_patient WHERE ssn="333333"),(SELECT ssn FROM doctor WHERE ssn="888888"),"1994-06-07",8,(SELECT trade_name FROM make_drug WHERE trade_name="drug8"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company8"));
INSERT INTO Prescription(pre_id,status,ssn,phy_ssn,pre_date,quantity,trade_name,pharm_co_name) 
VALUES (9,"yet-to-go",(SELECT ssn FROM pri_phy_patient WHERE ssn="222222"),(SELECT ssn FROM doctor WHERE ssn="999999"),"1994-06-07",9,(SELECT trade_name FROM make_drug WHERE trade_name="drug9"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company9"));
INSERT INTO Prescription(pre_id,status,ssn,phy_ssn,pre_date,quantity,trade_name,pharm_co_name) 
VALUES (10,"completed",(SELECT ssn FROM pri_phy_patient WHERE ssn="111111"),(SELECT ssn FROM doctor WHERE ssn="111222"),"1994-06-07",10,(SELECT trade_name FROM make_drug WHERE trade_name="drug10"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company10"));





INSERT INTO sell (price,pharm_id,trade_name,pharm_co_name) VALUES(14,(SELECT pharm_id FROM pharmacy WHERE pharm_id="1"),(SELECT trade_name FROM make_drug WHERE trade_name="drug1"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company1"));
INSERT INTO sell (price,pharm_id,trade_name,pharm_co_name) VALUES(15,(SELECT pharm_id FROM pharmacy WHERE pharm_id="2"),(SELECT trade_name FROM make_drug WHERE trade_name="drug2"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company2"));
INSERT INTO sell (price,pharm_id,trade_name,pharm_co_name) VALUES(16,(SELECT pharm_id FROM pharmacy WHERE pharm_id="3"),(SELECT trade_name FROM make_drug WHERE trade_name="drug3"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company3"));
INSERT INTO sell (price,pharm_id,trade_name,pharm_co_name) VALUES(17,(SELECT pharm_id FROM pharmacy WHERE pharm_id="4"),(SELECT trade_name FROM make_drug WHERE trade_name="drug4"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company4"));
INSERT INTO sell (price,pharm_id,trade_name,pharm_co_name) VALUES(18,(SELECT pharm_id FROM pharmacy WHERE pharm_id="5"),(SELECT trade_name FROM make_drug WHERE trade_name="drug5"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company5"));
INSERT INTO sell (price,pharm_id,trade_name,pharm_co_name) VALUES(19,(SELECT pharm_id FROM pharmacy WHERE pharm_id="6"),(SELECT trade_name FROM make_drug WHERE trade_name="drug6"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company6"));
INSERT INTO sell (price,pharm_id,trade_name,pharm_co_name) VALUES(10,(SELECT pharm_id FROM pharmacy WHERE pharm_id="7"),(SELECT trade_name FROM make_drug WHERE trade_name="drug7"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company7"));
INSERT INTO sell (price,pharm_id,trade_name,pharm_co_name) VALUES(19,(SELECT pharm_id FROM pharmacy WHERE pharm_id="8"),(SELECT trade_name FROM make_drug WHERE trade_name="drug8"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company8"));
INSERT INTO sell (price,pharm_id,trade_name,pharm_co_name) VALUES(18,(SELECT pharm_id FROM pharmacy WHERE pharm_id="9"),(SELECT trade_name FROM make_drug WHERE trade_name="drug9"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company9"));
INSERT INTO sell (price,pharm_id,trade_name,pharm_co_name) VALUES(14,(SELECT pharm_id FROM pharmacy WHERE pharm_id="10"),(SELECT trade_name FROM make_drug WHERE trade_name="drug10"),(SELECT pharm_co_name FROM make_drug WHERE pharm_co_name="Company10"));


INSERT INTO contract (pharm_id,start_date,end_date,text,supervisor,pharm_co_name) VALUES ((SELECT pharm_id FROM pharmacy WHERE pharm_id="1"),"2017-07-14","2018-07-13","All Copy Rights Reserved","Ben Aflick",(SELECT name FROM pharm_co WHERE name = "Company1"));
INSERT INTO contract (pharm_id,start_date,end_date,text,supervisor,pharm_co_name) VALUES ((SELECT pharm_id FROM pharmacy WHERE pharm_id="2"),"2017-07-14","2018-07-13","All Copy Rights Reserved","Ben Aflick",(SELECT name FROM pharm_co WHERE name = "Company2"));
INSERT INTO contract (pharm_id,start_date,end_date,text,supervisor,pharm_co_name) VALUES ((SELECT pharm_id FROM pharmacy WHERE pharm_id="3"),"2017-07-14","2018-07-13","All Copy Rights Reserved","Ben Aflick",(SELECT name FROM pharm_co WHERE name = "Company3"));
INSERT INTO contract (pharm_id,start_date,end_date,text,supervisor,pharm_co_name) VALUES ((SELECT pharm_id FROM pharmacy WHERE pharm_id="4"),"2017-07-14","2018-07-13","All Copy Rights Reserved","Ben Aflick",(SELECT name FROM pharm_co WHERE name = "Company4"));
INSERT INTO contract (pharm_id,start_date,end_date,text,supervisor,pharm_co_name) VALUES ((SELECT pharm_id FROM pharmacy WHERE pharm_id="5"),"2017-07-14","2018-07-13","All Copy Rights Reserved","Ben Aflick",(SELECT name FROM pharm_co WHERE name = "Company5"));
INSERT INTO contract (pharm_id,start_date,end_date,text,supervisor,pharm_co_name) VALUES ((SELECT pharm_id FROM pharmacy WHERE pharm_id="6"),"2017-07-14","2018-07-13","All Copy Rights Reserved","Ben Aflick",(SELECT name FROM pharm_co WHERE name = "Company6"));
INSERT INTO contract (pharm_id,start_date,end_date,text,supervisor,pharm_co_name) VALUES ((SELECT pharm_id FROM pharmacy WHERE pharm_id="7"),"2017-07-14","2018-07-13","All Copy Rights Reserved","Ben Aflick",(SELECT name FROM pharm_co WHERE name = "Company7"));
INSERT INTO contract (pharm_id,start_date,end_date,text,supervisor,pharm_co_name) VALUES ((SELECT pharm_id FROM pharmacy WHERE pharm_id="8"),"2017-07-14","2018-07-13","All Copy Rights Reserved","Ben Aflick",(SELECT name FROM pharm_co WHERE name = "Company8"));
INSERT INTO contract (pharm_id,start_date,end_date,text,supervisor,pharm_co_name) VALUES ((SELECT pharm_id FROM pharmacy WHERE pharm_id="9"),"2017-07-14","2018-07-13","All Copy Rights Reserved","Ben Aflick",(SELECT name FROM pharm_co WHERE name = "Company9"));
INSERT INTO contract (pharm_id,start_date,end_date,text,supervisor,pharm_co_name) VALUES ((SELECT pharm_id FROM pharmacy WHERE pharm_id="10"),"2017-07-14","2018-07-13","All Copy Rights Reserved","Ben Aflick",(SELECT name FROM pharm_co WHERE name = "Company10"));

