package mallorcatour.brains.neural.dafish;

import mallorcatour.neural.core.INeuralInfo;

class FlopNeuralDafish implements INeuralInfo {
private int[] layerSizeArray = new int[] {13, 27, 3};
private double[] weights = new double[] {56.27132145323635, 
128.66406770519274, 66.98107198742336, 68.9971465805241, 49.289919490557, 146.70899469191886, 109.27402556883413, 51.57306144086601, 23.813942125764097, 25.812396242668463, 60.123754651597636, 
-25.317119233849493, 84.89961730692542, -3.398716592333925, -55.64645058399779, 90.82928736214525, 209.57441715249306, 4.040185645743532, 265.8001100707947, 30.167468376281672, 22.69669228926905, 
4.910926335106297, 24.584743142734514, -3.5290914227650405, 40.7324193198971, 82.39366392234349, -64.6129207610972, -314.914168101981, 106.03857985967566, 80.79045178509068, -102.76954391136486, 
169.77666714886476, -116.59139972029345, 88.72329735866911, -180.89226843726786, -37.208632758436735, -47.36004989516727, 80.56381378931576, 24.161429677872636, -2.5430197583308747, 48.883761329648856, 
7.864908068843227, 63.21185797760046, -123.06822092543622, -32.01294254949129, -104.99157745413825, 392.77620401909326, 35.59570076654682, 228.44693533387257, -6.894151877891155, 4.219046026125915, 
-307.1679794648596, 72.72993643423463, 82.01589612160242, 234.12256899096798, 243.12686656138158, 31.042506359035865, 436.4923856631981, 157.02087802289606, 178.32180776358655, 83.50542000654352, 
126.50306820775434, 13.7169701686988, 31.251133061971316, 81.8044814484381, 49.71103212111001, 130.9733637482699, 40.841507889216146, 50.82475013188963, 49.045005815773216, 108.99068264777362, 
99.00550047399453, 30.134008381787105, 13.37249869224394, 11.073087189524083, 66.67965835784624, -34.66808691222448, 85.32494588763996, 111.70003695525712, -214.63010782075764, -16.51809114722219, 
72.04801105751739, 351.3500139014647, -54.47807318522533, -291.25690592894506, 41.43740654532449, 305.7042085222836, -16.496075673680746, -135.0484428592523, 142.38710147543424, -105.33676404953769, 
22.26196387353156, 145.94941494622051, -156.09097824154597, 0.5991183195298151, -11.152653796220354, 138.79634648526672, 255.55184530413152, -804.4156444585284, 115.52037548182689, -298.9866084087007, 
6.241827312224661, 85.33412959564092, 439.63174756698055, 63.06466439626781, 95.79966501863309, 156.19035124877735, 165.89186100746414, 37.9536102818933, 304.9162388428478, 131.19922480193648, 
118.80245127854482, 54.66925750020322, 79.05726641630196, 32.25360114223745, 8.508138185840782, 83.63167410185551, 55.662367729577404, 119.36843953535336, 123.73843336927148, 144.03425624093111, 
39.68072499743736, 263.5787156148055, 128.00547875148814, 100.82090807265485, 43.81681821048164, 68.36932804368293, 48.89507973059226, -11.867117740744682, 85.42489774534359, 176.67968374965915, 
0.6718993430589213, -18.104557568369795, 32.653275515510536, -29.01144190210044, -13.482951336513153, 45.29804795178922, -396.1892234008677, 6.631054372029236, -326.30524464789687, 122.16746718159162, 
-296.27760379736674, 400.0060764024421, 81.99584637092931, 173.7003948278578, 134.74765195431362, 106.8455872743123, 55.609797670216764, 246.13581921190263, 159.53362170472283, 122.9512818803228, 
57.118497995970635, 76.27374995552151, 62.34487617821771, -24.08099870581163, 85.47229879485879, 74.94399387473878, 0.25632119668395786, -23.38146449794106, 36.87418549166726, -27.158746557768435, 
16.09045250785035, 6.823721826473574, -100.35171916496594, -26.887072268133444, -95.88737093680876, 10.610352116645075, -82.23092481364833, 65.2094161391588, 71.34582550344332, 2.883710698590953, 
-14.714249132800719, 18.716754925686338, -36.258342582924605, -18.318835998072466, -10.211277464493579, -20.60274847076048, 151.1977653393045, -82.93857113013702, 36.837180044755165, -174.92851735045073, 
45.95836031420637, 52.13320362003353, 73.09670906386773, 88.46818205919729, 100.03892986382873, 42.146371226094224, 183.70392247923255, 92.91104301458584, 52.97854244337334, 25.343902979121744, 
27.00712423106602, 34.81615476218039, 3.8773953437924473, 83.4610972245098, -160.3031462334318, -224.55677405727417, -458.1643734603977, -264.2351242675509, -38.27696715158559, -654.9590209023514, 
-301.9073715060181, -445.15053320138594, -224.32199360223836, -313.2847413584517, 11.226271443614104, -39.024081423407374, 6.159208484811761, 143.56312851133708, -110.84057618712617, 234.8639584231129, 
117.49426024176407, 151.59342724394367, 186.47337985408254, 27.871518740016366, 108.13392286869428, 54.70066031644375, 8.823202017466139, -100.20103017298398, 130.03392751968545, 40.0071569244995, 
-84.09029605788618, 185.57839190212542, -268.9847102368275, -20.035221271046083, 151.64605467725949, -255.53486805755293, -83.37430476495054, -64.08350761383353, -42.79220153351983, -101.70959944970296, 
163.67980430463362, -80.94905960007189, 138.85999209236323, -60.282326245278874, -1099.7483190101882, -334.03970268343187, 884.5527389440472, -58.25706731307416, -553.6577015063923, -1386.3032544584416, 
-779.5224222406546, -398.342845576688, -1130.3632405215253, 25.153379888129887, -26.481721398272033, 645.0122898694207, 70.76856690461484, 128.31881316012107, 109.22057661179194, 91.9619708749416, 
51.58648877653001, 202.58946526504462, 127.83204342083027, 86.78849739054375, 41.39504091226062, 48.42579472220866, 49.81479143876616, -11.128195169799247, 84.19218251045494, -124.25757229784669, 
-9.15826151475346, 25.600084679798176, 7.640033478470718, 43.254932405374205, 3.7132087891019805, 24.971072115149752, 10.6893436693743, 15.363767506062771, 2.901545237994637, -4.059628574303033, 
170.13576682984177, -24.076450128204083, 67.6569346179885, -17.288091289768218, 22.21992036160188, -7.436787933479416, 70.94813016704012, -11.247230713528817, 68.30539163379999, -158.1948109468148, 
-102.69238152034022, -113.5516902930756, 53.37188428196956, -126.80366859489455, 70.31177333569948, 61.12320332964032, 98.79522338856756, 140.69514593390946, 149.91612380155414, 39.45605622400014, 
277.73406352132787, 126.2034351614598, 106.35937351306669, 48.89966125560308, 69.28500344596026, 35.989922677216654, 3.7604855348838018, 84.01628028033267, 87.43745239459942, -2.2590464645590536, 
-45.736327080137116, 19.844231665815066, -44.24403239592097, -5.292242331601876, -14.571912370947864, -23.024467566273984, 10.835970847085528, -39.43178352766859, 98.97106943925428, -170.40738165441545, 
12.821521982716968, 48.305315049035606, 74.93658275100354, 151.81830993794603, 189.55340205888697, 29.468825240006314, 320.0301885451574, 118.36699562232191, 110.30663508589932, 48.72529388509973, 
78.88393101191696, 28.372783757283297, 11.017619809811402, 84.10660986246766, -172.14004338569686, 4.706255205377598, -325.8092767874431, 230.1805938853253, -467.38942358673626, 39.519143112004684, 
-279.47359714377694, -1218.185806351655, -546.2694977875758, -662.7276534774634, -54.458764213221045, 73.23398023164803, 546.9305225305175, -2.499353960165166, -1.6062558115374164, 6.276119042991689, 
3.842573658174001, -0.22669494928976133, -3.036645198594689, 6.913657042737476, 4.115474302733528, -1.1993523561329047, -2.355143046626183, -6.20024364334729, -2.0059140615757522, -6.4163132367923605, 
-9.977272145893261, -1.2588548231240284, -4.48155466726039, 9.40968559010168, -5.583548626115532, -21.157774771130686, -1.5016372739273742, 9.225179777720443, -3.0281940217439387, -1.3926024978299139, 
-6.227120665549815, -1.6429670977273947, -24.60669857736004, -3.4228291427114534, -1.891495805414053, -2.470285834848951, 3.342522910079235, 2.3831541533529346, -4.611181679240197, -1.7247806391421012, 
7.553130846076157, 9.068860522891923, -3.290211708933002, -3.62431013592068, 5.613764747832931, -3.2592755703567504, 0.6053023833643583, -6.263615538946241, -1.2814851772708684, 7.895567519297449, 
9.07621769157869, 0.015399389318510558, 12.956845147791906, -1.989587049889512, 5.675898432007238, 3.2167045987298053, -3.018983716288426, -6.749412828677736, -3.8149512527822993, 8.065122454221422, 
-2.111431147050171, 1.7894631426906698, -0.4139259977465968, -6.485633285880243, -3.2682594976291335, 1.555071586263313, 1.8435442869405565, -7.079082099530932, -8.561949693888925, 1.5506944727583292, 
2.0667403539224427, 2.0375114732412283, 2.584901973149389, 2.3474239113411057, 7.512541452047356, 0.8132883466716934, -3.98549128875901, -5.0093259820766685, 1.6428361265769977, -10.56680335187965, 
1.6813811834919823, -4.876007699557562, -1.2684765316595368, 1.5504460395962811, 5.8386193447833525, 1.4780492232937086, -8.257257206769689, 1.7392485913832039};
public int[] getLayerSizes() {
return layerSizeArray;
}
public double[] getWeights() {
return weights;
}
}