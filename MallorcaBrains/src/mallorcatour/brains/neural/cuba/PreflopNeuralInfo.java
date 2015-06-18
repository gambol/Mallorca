package mallorcatour.brains.neural.cuba;

import mallorcatour.neural.core.INeuralInfo;

class PreflopNeuralInfo implements INeuralInfo {
private int[] layerSizeArray = new int[] {13, 27, 3};
private double[] weights = new double[] {2176.0025382899853, 
96.70502426349482, -358.6274430277644, 734.3678620300599, 125.44053166384876, 156.1762170406902, -363.12455719717843, 709.2332719044098, -70.63548116778347, -319.9951024202451, -12.13595238560707, 
111.73201367049391, -481.3735116237169, -14.699424833348013, -96.93526994927329, -77.7353720387646, -192.64675791251406, -30.138063835416197, -29.00000620478665, -86.72325324841589, -92.36290131197342, 
532.3358953659388, 25.222282080353143, -119.21442623161465, 229.77195214248104, 66.74071882334961, 74.59193119159241, 125.11625153962207, 121.84557915790066, -96.60371563557916, -29.3658198221577, 
-38.10969398768636, 117.78584434060829, -172.19378251473375, 376.8661057287649, 110.22638897432074, -5.82519099659352, 53.58546219809214, 183.84977877128856, 1027.6413340908084, 24.168096743585657, 
-147.45155575595294, 30.797521956108824, 167.85693414323984, 175.2547819069123, -149.0461856028645, 651.8854803259202, -258.12437372662873, -90.86815811114869, 77.08989843666029, 265.95713830300275, 
168.59388347346854, -36.06893852406343, 51.648353039361226, 241.03017861055042, -45.08529009221286, 32.40080741235256, 26.57476151937988, 230.49235205535393, -233.29873218796797, 563.1038617914922, 
97.92260930079605, -38.85187231278819, 49.0342373647013, 123.30810128887155, 123.30233455406076, 119.45055654366519, 40.48179991670694, -139.99408680477595, -15.334404954802686, -16.187860117788013, 
36.82124967111756, -12.503230050441237, 110.4664456702777, 73.43867870720811, 116.66666936526735, 75.1956216410093, 243.24950499002463, -2.0335557219191713, -17.161883253225117, 16.233215236345274, 
-36.94270438431393, -8.999036702681252, -10.308982921968266, 24.320094677715005, 2.776841555828392, 30.924604295974326, 4.698876121819694, -2.6516440429533814, -3.58172267348362, 9.658046412360703, 
1000.3903036666801, 384.69021627236776, -147.14291107701925, 352.73153844413616, -48.16525931390155, -37.14107084446623, -152.37549742445367, 58.57649577661668, 44.221557519727924, -136.7797111043507, 
33.80790294922765, 15.285105579477973, -5.263499946067088, -1640.028446444906, -710.9232472798994, 126.36268811713248, -758.7625834297731, 232.92776066701046, 239.60682197778735, 133.46798130011936, 
193.31994148052604, -540.6902860932684, 151.7133834800694, 343.63873604023905, 64.81457610284964, 287.56092973735684, 902.8656617751934, 38.20320413564379, 189.69736579268059, 341.76382432925567, 
185.90453850263924, 196.72740732515547, 189.06480422695623, 273.1251849654924, -119.73542134762852, -201.89454634413684, 125.89482272178114, 75.0231910378719, -106.74576837105418, -504.7249809805276, 
204.0724211049576, 157.87876093716676, -90.98501220732312, -17.209337828237334, -21.546047919182822, 165.89984597660438, -189.27802343291233, -113.97611232558747, 219.60183393737321, -335.13501830959484, 
34.81436572595028, 273.37841849755665, 170.18873075027392, 151.20034327913655, -31.78116772140267, -153.10775654651505, -65.23738302857359, -56.2327848323783, -31.221181419899636, 37.429407145435626, 
251.53113980221798, 74.61549289871523, 138.94709485217925, 92.1463612683942, 248.14849566821636, 120.56037773671048, 409.92127259028643, -13.161221296957454, 35.83767739449291, -176.18799453904973, 
-181.75004709457005, -18.253325307624106, -300.868106539841, 877.1140616596506, 199.59179532837402, -271.8499141154207, 167.12142344169897, 192.76027864012323, -0.1946566026195025, -71.29578168530631, 
352.2140899134675, -14.794064942751987, 142.1549683554666, 127.73792631619, 359.2204944899521, -259.15735077868356, -8.646618325600814, 229.9411466614152, -188.83562885477377, 23.55042016712979, 
71.26705594579876, -146.53785248338897, 266.11906564370634, -37.567386112166254, -0.08887727875242668, -1.282318693212657, -10.267381992364509, -35.14258209970199, -126.17922419518189, -122.59815884049614, 
96.75605067313282, 40.85376159982255, -25.311306874259177, 256.18840454186034, 218.7782912078941, 219.55806124884182, -74.8671999612032, 34.36961653517768, 5.875413685768881, -7.8037806779032195, 
-74.03427703455117, -5.913673303767623, -91.98097920455717, 65.79933906448665, -7.406071489955485, 12.55939915723257, 177.33095931110745, 55.84016662288689, 55.73150387242303, 12.20077234299239, 
-32.16621041760615, -28.417608430367636, -27.548870893678426, 6.782894637974229, -33.986315792261266, 229.1748679587201, 40.09706164163388, 15.289743345443657, 26.058057513176195, 60.39896933457531, 
556.9592075050269, 164.9191239199409, -426.31530797500744, 211.87438875496753, 203.66543263348413, 208.24478451564596, -419.09353775040205, 584.204817813783, -278.3084918057314, 30.35974653620381, 
34.22480467928656, 23.748310232518335, 161.1293059544132, -30.99269927709322, 59.360375082954356, 39.33872367322206, -60.73365731627168, 4.365942936332378, -1.109466824812926, 39.139179869633544, 
-98.26506855184945, 226.94748070849278, 83.32198284955605, -34.306064470581774, 23.995444364278534, 118.91381849575677, 38.864451597982956, 19.363020126537982, 11.484843830590476, -119.23449620471564, 
2.5159917123562923, -5.482003600420329, 11.773407144586539, -71.17846461247892, -31.542988355924997, 33.02336499132067, 31.18252206361779, -6.327634118292753, 133.07003649235722, -121.00694922547493, 
102.05506854065561, 0.21667434476201622, -28.145154464229503, 110.24806485165018, 123.83084028511738, 6.4375031638036795, 246.07754008357657, -946.0855468457084, -94.4552914720725, 296.72107792710193, 
59.31866377177208, 253.85234543313447, -139.46826937279926, 57.82857344409496, 12.565877448714112, -130.2852993070939, 40.16962157134501, 42.85820244078129, 15.070744547144496, 45.56498896380086, 
-111.31502886763082, 49.376206433295195, 116.79738928737984, 36.28260645373725, 230.9891887165683, 33.46637852007448, 15.985127530243908, -10.44103787040503, -25.233060633003916, 8.043355294611976, 
17.29584482250619, 1.0587427069557007, 4.438190797745963, 1.9670536232487337, -84.05325759877465, -32.294553135174915, -168.6929608727281, 58.53307597101703, 2725.970658371574, 603.0683601992416, 
-186.98215346133162, 971.7175235063789, 527.42269137757, 545.2479332688409, -190.9203609718712, 953.723553759892, -33.53389548755865, -177.59767400534008, 41.286955741638785, 348.79172870024024, 
176.36331299367703, 27.37563092648487, 248.30294218526217, -165.25805024484305, -61.9987865011669, -86.79718848594123, -90.21783197676231, -163.1664935152873, -86.02087657153132, 5.253678917175747, 
78.43647294590657, -23.661627725997146, 36.59043665524156, 220.0384608018833, -426.2435395385622, 56.29708021570884, 8.745072809799979, -151.09377011660928, 53.587565148541835, 55.49277342215557, 
6.769967027752644, 102.57121904722848, 148.45334230155538, 162.65659689857947, 149.5114042113277, 63.84956329709595, 263.0181655265928, -10.073445631645193, -6.379056883833345, 0.2037121400619634, 
-0.032234328287802455, 0.5679356282753678, -1.9294749712597399, 7.669829323231434, -3.7504708876663826, 8.647896569674632, -6.066892350525723, 12.773772048072725, 0.5713351483858508, 3.3805264732873694, 
11.91741697567969, 2.713495404166096, -0.6417669660611856, -3.6382588055702545, -0.6624848751383318, 1.6084139017685588, 4.472506115514247, -3.4208336945618107, -3.568801464701854, 1.0000028038405757, 
-17.121134344773004, 5.403011360792597, 7.65342116485142, -0.019959736576896082, 10.584011320081075, 9.424230028014847, -1.7035097542237276, -0.745416313156014, 0.6425233488479473, -0.19529124468585699, 
-19.247843651577192, 2.2031051259203225, -3.2801580423020726, 2.3489342046625015, -4.004663082086736, 8.901372093225635, 14.03296813570678, -4.471014424419048, -10.607818156940438, 5.072828830767722, 
4.150164382566378, 15.06744081441828, 0.8136008456956857, -3.911607325703919, -8.62056480534909, -2.447177485228091, -11.274885128331935, 1.6509622098464773, -5.460543320621394, 2.379941026303147, 
-26.381712377573525, 2.098977956753334, -7.429950200585398, -6.5369437226350975, -6.7293098809346965, -9.375996433332336, -4.745656976860623, 14.954946179050399, -1.6050912058329234, -1.2550523554608866, 
9.410645483098369, 2.6449829481817098, -11.176205616261779, -15.091000469888163, 2.752309949225206, 10.039627122978139, 24.03407080450888, -2.630485414530493, 17.27251537431623, -4.490665625769635, 
3.3849377162180807, 6.83060409313504, -2.4562829670839643, 9.52812225475476, -2.142155004948141, 3.9060714819352516, -2.22171503117227, -13.614022760205472};
public int[] getLayerSizes() {
return layerSizeArray;
}
public double[] getWeights() {
return weights;
}
}
