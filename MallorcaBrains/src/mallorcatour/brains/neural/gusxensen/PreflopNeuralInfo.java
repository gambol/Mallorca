package mallorcatour.brains.neural.gusxensen;

import mallorcatour.neural.core.INeuralInfo;

public class PreflopNeuralInfo implements INeuralInfo{
private int[] layerSizeArray = new int[] {13, 26, 3};
private double[] weights = new double[] {-50.977071778073096, 
348.90306740084054, 215.73268157945674, 58.25998122294223, 600.4661320691943, 280.11879395047987, 215.78198612292445, 479.16549589190043, 15.613387863621622, 443.21971618244334, 384.7140845251058, 
252.78494410342552, 570.5185418984349, 474.0585831301483, -18.632564978197898, -34.82361056038091, 27.384711638295006, -187.05267820979543, 21.515845117250624, -34.9002989577781, -229.34534793475845, 
-17.55640858478209, -250.58247884304885, 41.25614290560711, 189.4773558889269, -24.89507858738958, 812.1273049753403, 11.737964504948637, -429.85830706786385, -396.4550887530301, 279.3369608074452, 
20.595525245939896, -430.1385884988266, 435.20648317554424, -215.00687703789984, 31.419753719551583, -24.73665235356862, 121.63068602342605, 428.96876487421963, -390.8307728391496, 149.06430716363639, 
87.17978206976007, 98.11602703569308, 500.58644639123474, 57.25250505518289, 87.14507865579255, 471.54168857561217, -351.01407693696456, 304.34622527787474, 83.31842322777672, -1.0561532969298069, 
108.04029409824167, 279.8392980994534, -90.94746998476647, 232.3353881199348, 232.02300313084277, 372.90638482549986, 896.3354115047155, 231.97845037966562, 371.8743245557836, 298.56925690508587, 
52.32916958275402, 238.57526341625305, 241.5765175014999, 573.4131956521815, 618.2108376381368, -167.58254464979993, -85.77884671536833, -42.83465244523746, 121.6735123102953, 206.4586929891653, 
-86.05552204032247, 146.85409070565984, 278.87925372197094, -85.19606339537948, 80.26816211335196, -29.770214982838166, 82.27482219788513, -44.40436271664847, 147.8205339967603, 157.37222172604464, 
92.21245891341536, 353.51049737845415, 518.9578226756345, 157.89150518545182, 328.72274049419605, 55.21416782564158, 229.52749775578187, 194.33017167904174, 189.0213604330181, 574.755029845542, 
157.86198571955822, -45.45834131789668, -1.570314493791249, 18.673241566104448, -40.85700239270012, 97.33722218087402, -2.0866508008589424, 30.119630591585487, -52.585862800120786, -288.97119587478966, 
129.2391786324686, -167.18627621468377, 33.12602125527887, 443.4108402930809, -137.15640222831968, -133.47066650245077, -56.62119798173516, 23.71137656399524, 611.6683535443746, -133.4384717308927, 
94.64960336094649, 301.116186344736, -163.55275263923454, 182.55637255276721, 63.645675524180476, 530.8870784738552, -23.37997373840429, -275.8112820000793, 464.1474090392739, 438.6025119695256, 
357.281762340401, 1288.4656511029323, 464.2558494802059, 342.2192334151932, 366.8518752312288, 26.037023211726638, 235.82735330039884, 241.89246959896693, 574.1665282882285, -60.027168201874446, 
25.223971459392317, 203.30490171283606, 163.64969514873854, 296.2442610328129, 712.4235012281971, 203.01988205609592, 295.0765280761019, 115.66061778124282, 146.94217019001178, 153.63196678418305, 
181.98673738021117, 574.6628362764719, -3.052060622505832, 24.7658664679034, 34.55405124732337, -80.33760644982982, -14.217711093397906, -32.990017642406855, 34.74982881171114, -98.19659460592139, 
17.217120987735946, 309.048853370349, 81.60827644041163, -177.23645549769634, 72.46420360092013, 153.9668019272431, -198.82071387165897, 333.3622269071385, 331.15208463373733, 346.18153488437014, 
1103.6898746622403, 333.5190653223224, 347.84159663857133, 341.7049072583938, 17.31471474236599, 220.22943875220852, 237.51319164229756, 573.412103495612, 513.6661927730564, -135.17483361585846, 
31.69770095987709, 120.80725651029744, 195.19066240056188, 823.979046912926, 31.27802397247325, 279.0628688466471, 301.18404438786075, -114.36681571244667, 112.82183054329359, 190.6618797311576, 
568.3506500527315, 18.231248559031165, 39.21124088920074, -131.8462362404802, -17.070592798250352, 50.607197894413645, -31.277849265289042, -131.70215694739022, -3.292523837356857, -21.806516662209543, 
-187.48592493740634, 222.03425416447035, -226.7436524263676, 24.92883529776809, 730.6697942248901, 380.2966804163831, -283.7237787624076, -255.65011191867566, 363.92945619098265, -62.58870640282047, 
-283.7269103953669, 390.1657523953255, 42.66673702150566, 167.78495463908143, 203.29949908368116, 201.69574476868854, 573.185522445193, 695.1503566407378, 7.528628922093833, 59.8440258283405, 
95.8716453391448, 431.65171336492307, 660.7362208006388, 59.70215900054168, 439.37096658814795, 333.99531254882197, 34.26171712113173, 286.17407082920874, 274.3128290031527, 571.9918865367039, 
-23.90799619447719, -39.51177553557959, 248.6775586358243, 216.17136360982974, 309.2930306400755, 830.4589233168833, 248.27711903425836, 307.4816554136758, 175.31816310715362, 114.55860547070223, 
172.10918203390833, 196.2568994928483, 574.3725224209782, 567.5993405061747, 216.42155638500532, -98.41514773432044, -81.65806939887098, 371.7586187006325, 274.9743994033435, -98.2469414859026, 
386.4979979819917, 131.77080379504386, 131.35377486523936, 217.17336055734188, 219.79308225175146, 573.1958764402533, 293.8401326955309, 609.7651138124788, -189.54029137427653, -282.85080452819926, 
482.60861487952803, -319.5704666896198, -189.87149826136215, 424.7320058215204, -152.57175581081955, 433.2937543395831, 270.3720091811035, 199.53265260014032, 573.0845512594182, -5.270164943741415, 
-11.885873623986498, -2.5505929499303472, 16.965484983978158, -23.95191033877635, 0.45083257206162863, -2.4104017044327954, 59.82590259179597, -7.845119009522969, -113.53356335232037, 140.05145565920142, 
-40.40693228923672, -28.300919716970494, -51.017671975749096, -222.60936496710383, 427.5142437626425, 395.7201291723423, 348.2997956969881, 1192.3221186504443, 427.7028393691024, 334.0406660702852, 
319.0614943353776, 53.04413523436597, 222.88773571071633, 231.01007307650767, 574.2875965822384, -434.3259806441464, -2.9472367121246044, 338.23098097774715, 250.5321995472299, 271.38226762286206, 
828.8201306311494, 338.36479301947696, 250.2807829556037, 52.428687025761725, 207.0554935768454, 127.84405339747438, 157.15084543917163, 575.2184699957807, 1430.53476349987, 215.3875858619215, 
65.97311244971021, 249.91117350692863, 9.675200642455513, 3.979579787622602, 66.17978353470116, 62.08881380193122, 412.57620113730815, -465.3958843568699, 162.04375484680452, 511.9059527367017, 
-30.172837431719632, -1013.9331263290961, -522.2870460173098, 328.0493711037535, 335.26997053561075, 180.21355211374913, 500.0861687887372, 327.8978016176029, 126.50545039850603, -81.924338426656, 
195.9626002093676, -113.41917930295153, -74.363846032216, -356.8443115480611, 2.981063747644833, -3.2290613100509264, -0.8347574135787017, 0.739699294805174, -1.4400801279626971, -0.8436616853455877, 
1.6843664190318777, -1.7394717003031768, 3.4309875848933693, -0.5577644914356594, 1.0971961275424145, 2.5573829804447117, -1.2556497234157853, -3.2786923702794146, -1.283786588931976, -1.8191375446501687, 
-3.3801703841665747, 0.5715550197196917, -1.6326219496351275, 2.069924466445146, -3.0944209006280468, -0.1351885166401272, 3.183350303253932, -5.163576006614151, 2.124199973871835, 2.2522160258272494, 
-7.0710594938189955, -1.4084445663729823, 0.7381605173772261, -0.5007434634584386, 0.11335603034500981, 0.15461545487886272, -0.7615787888764295, 1.701810878745044, -1.2907600819966594, -0.5680598594971923, 
0.7055829317010446, -2.2147809076677283, 0.376628147306317, 5.3150995375470265, -2.118343152457861, 1.7960428373334967, 0.19071879694216218, 0.5526903628194565, 1.0896932520850005, -2.984408284994386, 
2.5648339791340846, -0.4779191741604035, -0.10330629594221637, 4.053253333828745, -1.3525410122512502, -1.4225124463863308, 4.459599902402475, 2.8170306745142057, 0.28472016577676285, 0.7347504047347888, 
-0.3982533839672945, 9.934885336211167E-4, 0.1761563813970501, -0.14273458962877966, -1.4835235262692177, -1.54036101848901, -1.1461885073061178, 2.002387679632626, -1.2901030302057743, -2.8047932523679258, 
2.269672209140184, 1.7105535268660048, 1.0313332524168495, -1.2232523639193384, 1.022704014411281, 4.089317258634086, -2.3932003382250135, -1.4519484329837427, -1.7853273427480023, -1.2493050306501614, 
-0.6171577123184595, -1.7200767304163949};
public int[] getLayerSizes() {
return layerSizeArray;
}
public double[] getWeights() {
return weights;
}
}
