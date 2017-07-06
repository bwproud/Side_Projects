/******************************************************************************
  
                      CAS ANALYTICS TEST LIBRARY
 
      NAME: cdtabv
 ACTIONSET: TKCASDT
    ACTION: DTREETRAIN/DTREESCORE
 REFERENCE:
   DEFECTS:
   HISTORY: 22Jun16 seuhuh created 
 TEST CASE: TREESPLIT
   PURPOSE: Build verification testing for DTREE actions. (CAS version of cdtbv) 
   DETAILS:
 
 ******************************************************************************/
options nonotes;
ods listing;

title1 "cdtabv: Build verification testing for DTREE actions. (CAS version of cdtbv)";

data val_temp;
 call streaminit(1234567);
 do id=1 to 200;
  ur = ranuni(1);
  v2c=put(ceil(RAND('UNIFORM')*3), 6.);
  v3c=put(ceil(RAND('UNIFORM')*4), 6.);
  v4c=put(ceil(RAND('UNIFORM')*5), 6.);
  v5c=put(ceil(RAND('UNIFORM')*6), 6.);
  v6c=put(ceil(RAND('UNIFORM')*7), 6.);
  v2 =v2c+ceil(RAND('UNIFORM')*5);
  v3 =v3c+ceil(RAND('UNIFORM')*4);
  v4 =v4c+ceil(RAND('UNIFORM')*3);
  v5 =v5c+ceil(RAND('UNIFORM')*2);
  v6 =v6c+ceil(RAND('UNIFORM')*1);
  v7 =v5 / (v6+1) + log10(1234+v4) - 20;
  v8 =v2 / v6 + log(12345+v3) - 30;
  v9 =v3 / v5;
  v10=v4 / sqrt(v6+5) + (v2*v5+1);
  v11=v2 / sqrt(v3+2) + log(v4*v6+1);
  e= (sqrt(1994/45)/3)*RAND('NORMAL');
  vi =5*v2 + 10*v3 + v4**2 + v5**2 + v6 + abs(5*v7 + 10*v8 + v9**2 + 5*v10**2 + v11) + e;
  vb =(vi>1000);
  vn =(vi>1000)+(vi>2000)+(vi>5000)+(vi>7500)+1;
  freq=ceil(RAND('UNIFORM')*150);
  if ur > 0.5      then do; part = "A"; end;
  else if ur > 0.1 then do; part = "B"; end;
                   else do; part = "C"; end;
  output;
 end;
run;
%casdist(libin=work, dsin=val_temp, dsout=val_temp);

/*ods select VariableImportance; 
ods output VariableImportance=vi1; 
PROC TREESPLIT DATA=sascas1.val_temp OUTMODEL=sascas1.dt1 MAXDEPTH=2 seed=54321 stat;
 INPUT v7 v8 v9 v10 v11/LEVEL=INTERVAL;
 INPUT v2c v3c v4c/LEVEL=NOMINAL;
 INPUT v5 v6/LEVEL=ordinal;
 TARGET vn /LEVEL=nominal;
 freq freq;
 partition rolevar=part(train="A" validate="B" test="C");
 GROW GINI;
 PRUNE cc;
 OUTPUT out=sascas1.score_out1 COPYVARS=(id vn v7 v8 v9 v10 v11 v2c v3c v4c v5 v6);
quit;
proc cas;
  history{first="-20"};
quit;*/
proc cas;
session sascas1;
loadactionset "Sampling";
loadactionset "decisionTree";
loadactionset "autotune";
run;
dtreeTrain / table={name='VAL_TEMP', where='part="A" and vn NE
      .', varList={{name='v7'}, {name='v8'}, {name='v9'}, {name='v10'}, {name='v11'},
      {name='v2c'}, {name='v3c'}, {name='v4c'}, {name='v5'}, {name='v6'}}}, target='vn',
      nominal={'v2c', 'v3c', 'v4c', 'vn'}, nBins=20, maxLevel=1, maxBranch=2, leafSize=5,
      crit='GINI', missing='USEINSEARCH', minUseInSearch=1, binOrder=true, varImp=true,
      stat=true, casOut={name='DT1', replace=true}, ordinal={'v5', 'v6'},
      ordinalBy={'ASCENDING', 'ASCENDING'}, freq='freq', mergeBin=true, encodeName=true; /*
      (SUCCESS) */
dtreePrune / table={name='VAL_TEMP', where='part="B" and vn NE
      .'}, model={name='DT1'}, costComplexity=true, varImp=true, casOut={name='DT1',
      replace=true}, freq='freq', encodeName=true; /* (SUCCESS) */
fetch / table={name='DT1'}, from=1, to=16384; /* (SUCCESS) */
dtreeScore / table={name='VAL_TEMP', where='part="A" and vn NE
      .'}, model={name='DT1'}, idVarList={'id', 'vn', 'v7', 'v8', 'v9', 'v10', 'v11', 'v2c',
      'v3c', 'v4c', 'v5', 'v6'}, noPath=true, freq='freq', encodeName=true; /* (SUCCESS) */
dtreeScore / table={name='VAL_TEMP', where='part="B" and vn NE
      .'}, model={name='DT1'}, idVarList={'id', 'vn', 'v7', 'v8', 'v9', 'v10', 'v11', 'v2c',
      'v3c', 'v4c', 'v5', 'v6'}, noPath=true, freq='freq', encodeName=true; /* (SUCCESS) */
dtreeScore / table={name='VAL_TEMP', where='part="C" and vn NE
      .'}, model={name='DT1'}, idVarList={'id', 'vn', 'v7', 'v8', 'v9', 'v10', 'v11', 'v2c',
      'v3c', 'v4c', 'v5', 'v6'}, noPath=true, freq='freq', encodeName=true; /* (SUCCESS) */
dtreeScore / table={name='VAL_TEMP'}, model={name='DT1'},
      idVarList={'id', 'vn', 'v7', 'v8', 'v9', 'v10', 'v11', 'v2c', 'v3c', 'v4c', 'v5', 'v6'},
      noPath=true, casOut={name='SCORE_OUT1', replace=true}, freq='freq', encodeName=true; /*
      (SUCCESS) */
dtreesplit result=r submit /
  table={ name="VAL_TEMP", varlist={'v7', 'v8', 'v9', 'v10', 'v11', 'v2c', 'v3c', 'v4c', 'v5', 'v6'} } 
  model={name= "DT1" }
  casout={name="DT1_split", replace=1} 
  target= "vi" freq="freq" 
  nominal={ 'v2c', 'v3c', 'v4c' } ordinal={ 'v5', 'v6' } ordinalby="ASCENDING" 
  maxlevel=1 maxbranch=2 nogreedy=0 binorder=1 varimp=0 stat=1 nomiss=1 mergeBin=0 encodeName=1 updatebin=1 
  code={labelid=10, comment=true, tabForm=true}
  nodeid={1};
 run;
dtreemerge result=r submit/
	casout={name="dt1_merge", replace=1}
    model={name="DT1_split"}
    varimp=true
	code={labelid=10, comment=true, tabForm=true} 
    /*nodeid={2 3}*/;
 run;     
quit;

/*ods select VariableImportance; 
ods output VariableImportance=vi2; 
PROC TREESPLIT DATA=sascas1.val_temp OUTMODEL=sascas1.dt2 MAXDEPTH=2 seed=54321 stat;
 INPUT v7 v8 v9 v10 v11/LEVEL=INTERVAL;
 INPUT v2c v3c v4c/LEVEL=NOMINAL;
 INPUT v5 v6/LEVEL=ordinal;
 TARGET vb /LEVEL=nominal;
 freq freq;
 *partition fraction(validate=0.3 test=0.2 seed=12345);
 GROW GINI;
 PRUNE none;
 OUTPUT out=sascas1.score_out2 COPYVARS=(id vn v7 v8 v9 v10 v11 v2c v3c v4c v5 v6);
 autotune maxtime=300 maxevals=3 popsize=2 MAXITERS=5 FRACTION=0.6;
quit;
proc cas;
  history{first="-20"};
quit;*/
proc cas;
session sascas1;
loadactionset "Sampling";
loadactionset "decisionTree";
loadactionset "autotune";
run;
tuneDecisionTree / tunerOptions={maxEvals=3, maxIters=5,
      maxTime=300, popSize=2, validationPartitionFraction=0.6, seed=54321},
      trainOptions={freq='freq', mergebin=true, table={name='VAL_TEMP', varlist={'v7', 'v8',
      'v9', 'v10', 'v11', 'v2c', 'v3c', 'v4c', 'v5', 'v6'}, where='vb NE .'},
      casout={name='DT2', replace=true}, target='vb', nominal={'v2c', 'v3c', 'v4c', 'vb'},
      ordinal={'v5', 'v6'}, ordinalby={'ASCENDING', 'ASCENDING'}, crit='GINI', nbins=100,
      maxlevel=3, maxbranch=2, leafsize=1, missing='USEINSEARCH', minuseinsearch=1,
      binorder=true, varimp=true, encodeName=true, stat=true}; /* (SUCCESS) */
dtreeTrain / table={name='VAL_TEMP', where='vb NE . AND _fold_
      GE  0.60', varList={{name='v7'}, {name='v8'}, {name='v9'}, {name='v10'}, {name='v11'},
      {name='v2c'}, {name='v3c'}, {name='v4c'}, {name='v5'}, {name='v6'}},
      compVars={'_fold_'}, compPgm="CALL STREAMINIT(54321);_fold_=RAND('uniform');"},
      target='vb', nominal={'v2c', 'v3c', 'v4c', 'vb'}, nBins=50, maxLevel=13, maxBranch=2,
      leafSize=1, crit=4, missing='USEINSEARCH', minUseInSearch=1, binOrder=true, varImp=true,
      stat=true, casOut={name='DT2', replace=true}, ordinal={'v5', 'v6'},
      ordinalBy={'ASCENDING', 'ASCENDING'}, freq='freq', mergeBin=true, encodeName=true; /*
      (SUCCESS) */
dtreeScore / table={name='VAL_TEMP', where='vb NE . AND _fold_
      LT  0.60', compVars={'_fold_'}, compPgm="CALL STREAMINIT(54321);_fold_=RAND('uniform');"}, model={name='DT2'}; /* (SUCCESS) */
fetch / table={name='DT2'}, from=1, to=1048575; /* (SUCCESS) */
dtreescore / table={name='VAL_TEMP'}, model={name='DT2'},
      idVarList={'id', 'vn', 'v7', 'v8', 'v9', 'v10', 'v11', 'v2c', 'v3c', 'v4c', 'v5', 'v6'},
      noPath=true, casOut={name='SCORE_OUT2', replace=true}, freq='freq', encodeName=true; /*
      (SUCCESS) */
dtreecode result=output/ table={name="VAL_TEMP" where='vb NE . AND _fold_ LT  0.60', compVars={'_fold_'}, compPgm="CALL STREAMINIT(54321);_fold_=RAND('uniform');"} 
                         model="DT2" code={labelid=10, comment=true};
      saveresult output['codegen'] replace dataset=codegen1;
quit;

/*ods select VariableImportance ; 
ods output VariableImportance=vi3; 
PROC TREESPLIT DATA=sascas1.val_temp OUTMODEL=sascas1.dt3 MAXDEPTH=2 seed=54321 stat;
 INPUT v7 v8 v9 v10 v11/LEVEL=INTERVAL;
 INPUT v2c v3c v4c/LEVEL=NOMINAL;
 INPUT v5 v6/LEVEL=ordinal;
 TARGET vi /LEVEL=INTERVAL;
 freq freq;
 *partition rolevar=part(train="A" validate="B" test="C");
 GROW variance;
 PRUNE none;
 OUTPUT out=sascas1.score_out3 COPYVARS=(id vn v7 v8 v9 v10 v11 v2c v3c v4c v5 v6);
 autotune maxtime=300 maxevals=3 popsize=2 MAXITERS=5 KFOLD=2;
quit;
proc cas;
  history{first="-20"};
quit;*/
proc cas;
session sascas1;
loadactionset "Sampling";
loadactionset "decisionTree";
loadactionset "autotune";
run;
tuneDecisionTree / tunerOptions={maxEvals=3, maxIters=5,
      maxTime=300, popSize=2, nCrossValFolds=2, seed=54321}, trainOptions={freq='freq',
      mergebin=true, table={name='VAL_TEMP', varlist={'v7', 'v8', 'v9', 'v10', 'v11', 'v2c',
      'v3c', 'v4c', 'v5', 'v6'}}, casout={name='DT3', replace=true}, target='vi',
      nominal={'v2c', 'v3c', 'v4c'}, ordinal={'v5', 'v6'}, ordinalby={'ASCENDING',
      'ASCENDING'}, crit='VARIANCE', nbins=100, maxlevel=3, maxbranch=2, leafsize=1,
      missing='USEINSEARCH', minuseinsearch=1, binorder=true, varimp=true, encodeName=true,
      stat=true}; /* (SUCCESS) */
dtreeTrain / table={name='VAL_TEMP', where='',
      varList={{name='v7'}, {name='v8'}, {name='v9'}, {name='v10'}, {name='v11'},
      {name='v2c'}, {name='v3c'}, {name='v4c'}, {name='v5'}, {name='v6'}},
      compVars={'_fold_'}, compPgm="CALL STREAMINIT(54321);_fold_=FLOOR(2*RAND('uniform'))+1;"}, target='vi', nominal={'v2c',
      'v3c', 'v4c'}, nBins=50, maxLevel=13, maxBranch=2, leafSize=1, crit=6,
      missing='USEINSEARCH', minUseInSearch=1, binOrder=true, varImp=true, stat=true,
      casOut={name='DT3', replace=true}, ordinal={'v5', 'v6'}, ordinalBy={'ASCENDING',
      'ASCENDING'}, freq='freq', mergeBin=true, encodeName=true; /* (SUCCESS) */
dtreeScore / table={name='VAL_TEMP', where='',
      compVars={'_fold_'}, compPgm="CALL STREAMINIT(54321);_fold_=FLOOR(2*RAND('uniform'))+1;"}, model={name='DT3'}; /*
      (SUCCESS) */
fetch / table={name='DT3'}, from=1, to=1048575; /* (SUCCESS) */
dtreescore / table={name='VAL_TEMP'}, model={name='DT3'},
      idVarList={'id', 'vn', 'v7', 'v8', 'v9', 'v10', 'v11', 'v2c', 'v3c', 'v4c', 'v5', 'v6'},
      noPath=true, casOut={name='SCORE_OUT3', replace=true}, freq='freq', encodeName=true; /*
      (SUCCESS) */
quit;

data vi3_comp(label='Variable Importance');
 input Variable $ Importance Std RelativeImportance;
 label RelativeImportance='Relative Importance';
cards;
v10           2.228E11      2.958E10        1.0000
v4c           1.1797E8      13296606        0.0005
v3c           31939529       1948034        0.0001
v11           21511101       3819456        0.0001
v5            11121485       2113205        499E-7
v2c           10342221        953473        464E-7
v7             9636974       1723533        432E-7
v8             8193836       1005743        368E-7
v9             2503836        413574        112E-7
;
run;
/*proc compare base=vi3 compare=vi3_comp method=absolute criterion=1e-4 warn noprint; 
 var RelativeImportance;
run;*/

%CASCLEAR;
