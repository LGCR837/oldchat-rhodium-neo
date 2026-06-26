package org.spongycastle.math.ec;

import android.R;
import java.math.BigInteger;
import org.spongycastle.asn1.cmc.BodyPartID;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class LongArray implements Cloneable {
    private static final String ZEROES = "0000000000000000000000000000000000000000000000000000000000000000";
    private long[] m_ints;
    private static final short[] INTERLEAVE2_TABLE = {0, 1, 4, 5, 16, 17, 20, 21, 64, 65, 68, 69, 80, 81, 84, 85, 256, 257, 260, 261, 272, 273, 276, 277, 320, 321, 324, 325, 336, 337, 340, 341, 1024, 1025, 1028, 1029, 1040, 1041, 1044, 1045, 1088, 1089, 1092, 1093, 1104, 1105, 1108, 1109, 1280, 1281, 1284, 1285, 1296, 1297, 1300, 1301, 1344, 1345, 1348, 1349, 1360, 1361, 1364, 1365, 4096, 4097, 4100, 4101, 4112, 4113, 4116, 4117, 4160, 4161, 4164, 4165, 4176, 4177, 4180, 4181, 4352, 4353, 4356, 4357, 4368, 4369, 4372, 4373, 4416, 4417, 4420, 4421, 4432, 4433, 4436, 4437, 5120, 5121, 5124, 5125, 5136, 5137, 5140, 5141, 5184, 5185, 5188, 5189, 5200, 5201, 5204, 5205, 5376, 5377, 5380, 5381, 5392, 5393, 5396, 5397, 5440, 5441, 5444, 5445, 5456, 5457, 5460, 5461, 16384, 16385, 16388, 16389, 16400, 16401, 16404, 16405, 16448, 16449, 16452, 16453, 16464, 16465, 16468, 16469, 16640, 16641, 16644, 16645, 16656, 16657, 16660, 16661, 16704, 16705, 16708, 16709, 16720, 16721, 16724, 16725, 17408, 17409, 17412, 17413, 17424, 17425, 17428, 17429, 17472, 17473, 17476, 17477, 17488, 17489, 17492, 17493, 17664, 17665, 17668, 17669, 17680, 17681, 17684, 17685, 17728, 17729, 17732, 17733, 17744, 17745, 17748, 17749, 20480, 20481, 20484, 20485, 20496, 20497, 20500, 20501, 20544, 20545, 20548, 20549, 20560, 20561, 20564, 20565, 20736, 20737, 20740, 20741, 20752, 20753, 20756, 20757, 20800, 20801, 20804, 20805, 20816, 20817, 20820, 20821, 21504, 21505, 21508, 21509, 21520, 21521, 21524, 21525, 21568, 21569, 21572, 21573, 21584, 21585, 21588, 21589, 21760, 21761, 21764, 21765, 21776, 21777, 21780, 21781, 21824, 21825, 21828, 21829, 21840, 21841, 21844, 21845};
    private static final int[] INTERLEAVE3_TABLE = {0, 1, 8, 9, 64, 65, 72, 73, 512, 513, 520, 521, 576, 577, 584, 585, PKIFailureInfo.certConfirmed, 4097, 4104, 4105, 4160, 4161, 4168, 4169, 4608, 4609, 4616, 4617, 4672, 4673, 4680, 4681, 32768, 32769, 32776, 32777, 32832, 32833, 32840, 32841, 33280, 33281, 33288, 33289, 33344, 33345, 33352, 33353, 36864, 36865, 36872, 36873, 36928, 36929, 36936, 36937, 37376, 37377, 37384, 37385, 37440, 37441, 37448, 37449, PKIFailureInfo.transactionIdInUse, 262145, 262152, 262153, 262208, 262209, 262216, 262217, 262656, 262657, 262664, 262665, 262720, 262721, 262728, 262729, 266240, 266241, 266248, 266249, 266304, 266305, 266312, 266313, 266752, 266753, 266760, 266761, 266816, 266817, 266824, 266825, 294912, 294913, 294920, 294921, 294976, 294977, 294984, 294985, 295424, 295425, 295432, 295433, 295488, 295489, 295496, 295497, 299008, 299009, 299016, 299017, 299072, 299073, 299080, 299081, 299520, 299521, 299528, 299529, 299584, 299585, 299592, 299593};
    private static final int[] INTERLEAVE4_TABLE = {0, 1, 16, 17, 256, 257, 272, 273, PKIFailureInfo.certConfirmed, 4097, 4112, 4113, 4352, 4353, 4368, 4369, PKIFailureInfo.notAuthorized, 65537, 65552, 65553, 65792, 65793, 65808, 65809, 69632, 69633, 69648, 69649, 69888, 69889, 69904, 69905, PKIFailureInfo.badCertTemplate, 1048577, 1048592, 1048593, 1048832, 1048833, 1048848, 1048849, 1052672, 1052673, 1052688, 1052689, 1052928, 1052929, 1052944, 1052945, 1114112, 1114113, 1114128, 1114129, 1114368, 1114369, 1114384, 1114385, 1118208, 1118209, 1118224, 1118225, 1118464, 1118465, 1118480, 1118481, 16777216, 16777217, 16777232, 16777233, 16777472, 16777473, 16777488, 16777489, 16781312, 16781313, 16781328, 16781329, 16781568, 16781569, 16781584, 16781585, R.attr.theme, R.attr.label, R.attr.exported, R.attr.process, R.attr.transcriptMode, R.attr.cacheColorHint, R.attr.childIndicatorRight, R.attr.childDivider, 16846848, 16846849, 16846864, 16846865, 16847104, 16847105, 16847120, 16847121, R.raw.loaderror, R.raw.nodomain, 17825808, 17825809, 17826048, 17826049, 17826064, 17826065, 17829888, 17829889, 17829904, 17829905, 17830144, 17830145, 17830160, 17830161, R.bool.config_sendPackageName, R.bool.config_showDefaultAssistant, R.bool.allow_test_udfps, R.bool.auto_data_switch_ping_test_before_switch, R.bool.config_cecRoutingControl_userConfigurable, R.bool.config_cecSetMenuLanguageDisabled_allowed, R.bool.config_cecSystemAudioModeMutingDisabled_allowed, R.bool.config_cecSystemAudioModeMutingDisabled_default, 17895424, 17895425, 17895440, 17895441, 17895680, 17895681, 17895696, 17895697, 268435456, 268435457, 268435472, 268435473, 268435712, 268435713, 268435728, 268435729, 268439552, 268439553, 268439568, 268439569, 268439808, 268439809, 268439824, 268439825, 268500992, 268500993, 268501008, 268501009, 268501248, 268501249, 268501264, 268501265, 268505088, 268505089, 268505104, 268505105, 268505344, 268505345, 268505360, 268505361, 269484032, 269484033, 269484048, 269484049, 269484288, 269484289, 269484304, 269484305, 269488128, 269488129, 269488144, 269488145, 269488384, 269488385, 269488400, 269488401, 269549568, 269549569, 269549584, 269549585, 269549824, 269549825, 269549840, 269549841, 269553664, 269553665, 269553680, 269553681, 269553920, 269553921, 269553936, 269553937, 285212672, 285212673, 285212688, 285212689, 285212928, 285212929, 285212944, 285212945, 285216768, 285216769, 285216784, 285216785, 285217024, 285217025, 285217040, 285217041, 285278208, 285278209, 285278224, 285278225, 285278464, 285278465, 285278480, 285278481, 285282304, 285282305, 285282320, 285282321, 285282560, 285282561, 285282576, 285282577, 286261248, 286261249, 286261264, 286261265, 286261504, 286261505, 286261520, 286261521, 286265344, 286265345, 286265360, 286265361, 286265600, 286265601, 286265616, 286265617, 286326784, 286326785, 286326800, 286326801, 286327040, 286327041, 286327056, 286327057, 286330880, 286330881, 286330896, 286330897, 286331136, 286331137, 286331152, 286331153};
    private static final int[] INTERLEAVE5_TABLE = {0, 1, 32, 33, 1024, 1025, 1056, 1057, 32768, 32769, 32800, 32801, 33792, 33793, 33824, 33825, PKIFailureInfo.badCertTemplate, 1048577, 1048608, 1048609, 1049600, 1049601, 1049632, 1049633, 1081344, 1081345, 1081376, 1081377, 1082368, 1082369, 1082400, 1082401, 33554432, 33554433, 33554464, 33554465, 33555456, 33555457, 33555488, 33555489, 33587200, 33587201, 33587232, 33587233, 33588224, 33588225, 33588256, 33588257, 34603008, 34603009, 34603040, 34603041, 34604032, 34604033, 34604064, 34604065, 34635776, 34635777, 34635808, 34635809, 34636800, 34636801, 34636832, 34636833, 1073741824, 1073741825, 1073741856, 1073741857, 1073742848, 1073742849, 1073742880, 1073742881, 1073774592, 1073774593, 1073774624, 1073774625, 1073775616, 1073775617, 1073775648, 1073775649, 1074790400, 1074790401, 1074790432, 1074790433, 1074791424, 1074791425, 1074791456, 1074791457, 1074823168, 1074823169, 1074823200, 1074823201, 1074824192, 1074824193, 1074824224, 1074824225, 1107296256, 1107296257, 1107296288, 1107296289, 1107297280, 1107297281, 1107297312, 1107297313, 1107329024, 1107329025, 1107329056, 1107329057, 1107330048, 1107330049, 1107330080, 1107330081, 1108344832, 1108344833, 1108344864, 1108344865, 1108345856, 1108345857, 1108345888, 1108345889, 1108377600, 1108377601, 1108377632, 1108377633, 1108378624, 1108378625, 1108378656, 1108378657};
    private static final long[] INTERLEAVE7_TABLE = {0, 1, 128, 129, 16384, 16385, 16512, 16513, 2097152, 2097153, 2097280, 2097281, 2113536, 2113537, 2113664, 2113665, 268435456, 268435457, 268435584, 268435585, 268451840, 268451841, 268451968, 268451969, 270532608, 270532609, 270532736, 270532737, 270548992, 270548993, 270549120, 270549121, 34359738368L, 34359738369L, 34359738496L, 34359738497L, 34359754752L, 34359754753L, 34359754880L, 34359754881L, 34361835520L, 34361835521L, 34361835648L, 34361835649L, 34361851904L, 34361851905L, 34361852032L, 34361852033L, 34628173824L, 34628173825L, 34628173952L, 34628173953L, 34628190208L, 34628190209L, 34628190336L, 34628190337L, 34630270976L, 34630270977L, 34630271104L, 34630271105L, 34630287360L, 34630287361L, 34630287488L, 34630287489L, 4398046511104L, 4398046511105L, 4398046511232L, 4398046511233L, 4398046527488L, 4398046527489L, 4398046527616L, 4398046527617L, 4398048608256L, 4398048608257L, 4398048608384L, 4398048608385L, 4398048624640L, 4398048624641L, 4398048624768L, 4398048624769L, 4398314946560L, 4398314946561L, 4398314946688L, 4398314946689L, 4398314962944L, 4398314962945L, 4398314963072L, 4398314963073L, 4398317043712L, 4398317043713L, 4398317043840L, 4398317043841L, 4398317060096L, 4398317060097L, 4398317060224L, 4398317060225L, 4432406249472L, 4432406249473L, 4432406249600L, 4432406249601L, 4432406265856L, 4432406265857L, 4432406265984L, 4432406265985L, 4432408346624L, 4432408346625L, 4432408346752L, 4432408346753L, 4432408363008L, 4432408363009L, 4432408363136L, 4432408363137L, 4432674684928L, 4432674684929L, 4432674685056L, 4432674685057L, 4432674701312L, 4432674701313L, 4432674701440L, 4432674701441L, 4432676782080L, 4432676782081L, 4432676782208L, 4432676782209L, 4432676798464L, 4432676798465L, 4432676798592L, 4432676798593L, 562949953421312L, 562949953421313L, 562949953421440L, 562949953421441L, 562949953437696L, 562949953437697L, 562949953437824L, 562949953437825L, 562949955518464L, 562949955518465L, 562949955518592L, 562949955518593L, 562949955534848L, 562949955534849L, 562949955534976L, 562949955534977L, 562950221856768L, 562950221856769L, 562950221856896L, 562950221856897L, 562950221873152L, 562950221873153L, 562950221873280L, 562950221873281L, 562950223953920L, 562950223953921L, 562950223954048L, 562950223954049L, 562950223970304L, 562950223970305L, 562950223970432L, 562950223970433L, 562984313159680L, 562984313159681L, 562984313159808L, 562984313159809L, 562984313176064L, 562984313176065L, 562984313176192L, 562984313176193L, 562984315256832L, 562984315256833L, 562984315256960L, 562984315256961L, 562984315273216L, 562984315273217L, 562984315273344L, 562984315273345L, 562984581595136L, 562984581595137L, 562984581595264L, 562984581595265L, 562984581611520L, 562984581611521L, 562984581611648L, 562984581611649L, 562984583692288L, 562984583692289L, 562984583692416L, 562984583692417L, 562984583708672L, 562984583708673L, 562984583708800L, 562984583708801L, 567347999932416L, 567347999932417L, 567347999932544L, 567347999932545L, 567347999948800L, 567347999948801L, 567347999948928L, 567347999948929L, 567348002029568L, 567348002029569L, 567348002029696L, 567348002029697L, 567348002045952L, 567348002045953L, 567348002046080L, 567348002046081L, 567348268367872L, 567348268367873L, 567348268368000L, 567348268368001L, 567348268384256L, 567348268384257L, 567348268384384L, 567348268384385L, 567348270465024L, 567348270465025L, 567348270465152L, 567348270465153L, 567348270481408L, 567348270481409L, 567348270481536L, 567348270481537L, 567382359670784L, 567382359670785L, 567382359670912L, 567382359670913L, 567382359687168L, 567382359687169L, 567382359687296L, 567382359687297L, 567382361767936L, 567382361767937L, 567382361768064L, 567382361768065L, 567382361784320L, 567382361784321L, 567382361784448L, 567382361784449L, 567382628106240L, 567382628106241L, 567382628106368L, 567382628106369L, 567382628122624L, 567382628122625L, 567382628122752L, 567382628122753L, 567382630203392L, 567382630203393L, 567382630203520L, 567382630203521L, 567382630219776L, 567382630219777L, 567382630219904L, 567382630219905L, 72057594037927936L, 72057594037927937L, 72057594037928064L, 72057594037928065L, 72057594037944320L, 72057594037944321L, 72057594037944448L, 72057594037944449L, 72057594040025088L, 72057594040025089L, 72057594040025216L, 72057594040025217L, 72057594040041472L, 72057594040041473L, 72057594040041600L, 72057594040041601L, 72057594306363392L, 72057594306363393L, 72057594306363520L, 72057594306363521L, 72057594306379776L, 72057594306379777L, 72057594306379904L, 72057594306379905L, 72057594308460544L, 72057594308460545L, 72057594308460672L, 72057594308460673L, 72057594308476928L, 72057594308476929L, 72057594308477056L, 72057594308477057L, 72057628397666304L, 72057628397666305L, 72057628397666432L, 72057628397666433L, 72057628397682688L, 72057628397682689L, 72057628397682816L, 72057628397682817L, 72057628399763456L, 72057628399763457L, 72057628399763584L, 72057628399763585L, 72057628399779840L, 72057628399779841L, 72057628399779968L, 72057628399779969L, 72057628666101760L, 72057628666101761L, 72057628666101888L, 72057628666101889L, 72057628666118144L, 72057628666118145L, 72057628666118272L, 72057628666118273L, 72057628668198912L, 72057628668198913L, 72057628668199040L, 72057628668199041L, 72057628668215296L, 72057628668215297L, 72057628668215424L, 72057628668215425L, 72061992084439040L, 72061992084439041L, 72061992084439168L, 72061992084439169L, 72061992084455424L, 72061992084455425L, 72061992084455552L, 72061992084455553L, 72061992086536192L, 72061992086536193L, 72061992086536320L, 72061992086536321L, 72061992086552576L, 72061992086552577L, 72061992086552704L, 72061992086552705L, 72061992352874496L, 72061992352874497L, 72061992352874624L, 72061992352874625L, 72061992352890880L, 72061992352890881L, 72061992352891008L, 72061992352891009L, 72061992354971648L, 72061992354971649L, 72061992354971776L, 72061992354971777L, 72061992354988032L, 72061992354988033L, 72061992354988160L, 72061992354988161L, 72062026444177408L, 72062026444177409L, 72062026444177536L, 72062026444177537L, 72062026444193792L, 72062026444193793L, 72062026444193920L, 72062026444193921L, 72062026446274560L, 72062026446274561L, 72062026446274688L, 72062026446274689L, 72062026446290944L, 72062026446290945L, 72062026446291072L, 72062026446291073L, 72062026712612864L, 72062026712612865L, 72062026712612992L, 72062026712612993L, 72062026712629248L, 72062026712629249L, 72062026712629376L, 72062026712629377L, 72062026714710016L, 72062026714710017L, 72062026714710144L, 72062026714710145L, 72062026714726400L, 72062026714726401L, 72062026714726528L, 72062026714726529L, 72620543991349248L, 72620543991349249L, 72620543991349376L, 72620543991349377L, 72620543991365632L, 72620543991365633L, 72620543991365760L, 72620543991365761L, 72620543993446400L, 72620543993446401L, 72620543993446528L, 72620543993446529L, 72620543993462784L, 72620543993462785L, 72620543993462912L, 72620543993462913L, 72620544259784704L, 72620544259784705L, 72620544259784832L, 72620544259784833L, 72620544259801088L, 72620544259801089L, 72620544259801216L, 72620544259801217L, 72620544261881856L, 72620544261881857L, 72620544261881984L, 72620544261881985L, 72620544261898240L, 72620544261898241L, 72620544261898368L, 72620544261898369L, 72620578351087616L, 72620578351087617L, 72620578351087744L, 72620578351087745L, 72620578351104000L, 72620578351104001L, 72620578351104128L, 72620578351104129L, 72620578353184768L, 72620578353184769L, 72620578353184896L, 72620578353184897L, 72620578353201152L, 72620578353201153L, 72620578353201280L, 72620578353201281L, 72620578619523072L, 72620578619523073L, 72620578619523200L, 72620578619523201L, 72620578619539456L, 72620578619539457L, 72620578619539584L, 72620578619539585L, 72620578621620224L, 72620578621620225L, 72620578621620352L, 72620578621620353L, 72620578621636608L, 72620578621636609L, 72620578621636736L, 72620578621636737L, 72624942037860352L, 72624942037860353L, 72624942037860480L, 72624942037860481L, 72624942037876736L, 72624942037876737L, 72624942037876864L, 72624942037876865L, 72624942039957504L, 72624942039957505L, 72624942039957632L, 72624942039957633L, 72624942039973888L, 72624942039973889L, 72624942039974016L, 72624942039974017L, 72624942306295808L, 72624942306295809L, 72624942306295936L, 72624942306295937L, 72624942306312192L, 72624942306312193L, 72624942306312320L, 72624942306312321L, 72624942308392960L, 72624942308392961L, 72624942308393088L, 72624942308393089L, 72624942308409344L, 72624942308409345L, 72624942308409472L, 72624942308409473L, 72624976397598720L, 72624976397598721L, 72624976397598848L, 72624976397598849L, 72624976397615104L, 72624976397615105L, 72624976397615232L, 72624976397615233L, 72624976399695872L, 72624976399695873L, 72624976399696000L, 72624976399696001L, 72624976399712256L, 72624976399712257L, 72624976399712384L, 72624976399712385L, 72624976666034176L, 72624976666034177L, 72624976666034304L, 72624976666034305L, 72624976666050560L, 72624976666050561L, 72624976666050688L, 72624976666050689L, 72624976668131328L, 72624976668131329L, 72624976668131456L, 72624976668131457L, 72624976668147712L, 72624976668147713L, 72624976668147840L, 72624976668147841L};
    static final byte[] bitLengths = {0, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};

    public LongArray(int i2) {
        this.m_ints = new long[i2];
    }

    private static void add(long[] jArr, int i2, long[] jArr2, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i2 + i5;
            jArr[i6] = jArr[i6] ^ jArr2[i3 + i5];
        }
    }

    private static void addBoth(long[] jArr, int i2, long[] jArr2, int i3, long[] jArr3, int i4, int i5) {
        for (int i6 = 0; i6 < i5; i6++) {
            int i7 = i2 + i6;
            jArr[i7] = jArr[i7] ^ (jArr2[i3 + i6] ^ jArr3[i4 + i6]);
        }
    }

    private void addShiftedByBitsSafe(LongArray longArray, int i2, int i3) {
        int i4 = (i2 + 63) >>> 6;
        int i5 = i3 >>> 6;
        int i6 = i3 & 63;
        if (i6 == 0) {
            add(this.m_ints, i5, longArray.m_ints, 0, i4);
            return;
        }
        long jAddShiftedUp = addShiftedUp(this.m_ints, i5, longArray.m_ints, 0, i4, i6);
        if (jAddShiftedUp != 0) {
            long[] jArr = this.m_ints;
            int i7 = i4 + i5;
            jArr[i7] = jAddShiftedUp ^ jArr[i7];
        }
    }

    private static long addShiftedDown(long[] jArr, int i2, long[] jArr2, int i3, int i4, int i5) {
        int i6 = 64 - i5;
        long j2 = 0;
        while (true) {
            i4--;
            if (i4 < 0) {
                return j2;
            }
            long j3 = jArr2[i3 + i4];
            int i7 = i2 + i4;
            jArr[i7] = (j2 | (j3 >>> i5)) ^ jArr[i7];
            j2 = j3 << i6;
        }
    }

    private static long addShiftedUp(long[] jArr, int i2, long[] jArr2, int i3, int i4, int i5) {
        int i6 = 64 - i5;
        long j2 = 0;
        for (int i7 = 0; i7 < i4; i7++) {
            long j3 = jArr2[i3 + i7];
            int i8 = i2 + i7;
            jArr[i8] = (j2 | (j3 << i5)) ^ jArr[i8];
            j2 = j3 >>> i6;
        }
        return j2;
    }

    private static int bitLength(long j2) {
        int i2;
        int i3 = 32;
        int i4 = (int) (j2 >>> 32);
        if (i4 == 0) {
            i4 = (int) j2;
            i3 = 0;
        }
        int i5 = i4 >>> 16;
        if (i5 == 0) {
            int i6 = i4 >>> 8;
            i2 = i6 == 0 ? bitLengths[i4] : bitLengths[i6] + 8;
        } else {
            int i7 = i4 >>> 24;
            i2 = i7 == 0 ? bitLengths[i5] + Tnaf.POW_2_WIDTH : bitLengths[i7] + 24;
        }
        return i3 + i2;
    }

    private int degreeFrom(int i2) {
        int i3 = (i2 + 62) >>> 6;
        while (i3 != 0) {
            i3--;
            long j2 = this.m_ints[i3];
            if (j2 != 0) {
                return (i3 << 6) + bitLength(j2);
            }
        }
        return 0;
    }

    private static void distribute(long[] jArr, int i2, int i3, int i4, int i5) {
        for (int i6 = 0; i6 < i5; i6++) {
            long j2 = jArr[i2 + i6];
            int i7 = i3 + i6;
            jArr[i7] = jArr[i7] ^ j2;
            int i8 = i4 + i6;
            jArr[i8] = j2 ^ jArr[i8];
        }
    }

    private static void flipBit(long[] jArr, int i2, int i3) {
        int i4 = i2 + (i3 >>> 6);
        jArr[i4] = (1 << (i3 & 63)) ^ jArr[i4];
    }

    private static void flipVector(long[] jArr, int i2, long[] jArr2, int i3, int i4, int i5) {
        int i6 = i2 + (i5 >>> 6);
        int i7 = i5 & 63;
        if (i7 == 0) {
            add(jArr, i6, jArr2, i3, i4);
        } else {
            jArr[i6] = addShiftedDown(jArr, i6 + 1, jArr2, i3, i4, 64 - i7) ^ jArr[i6];
        }
    }

    private static void flipWord(long[] jArr, int i2, int i3, long j2) {
        int i4 = i2 + (i3 >>> 6);
        int i5 = i3 & 63;
        if (i5 == 0) {
            jArr[i4] = j2 ^ jArr[i4];
            return;
        }
        jArr[i4] = jArr[i4] ^ (j2 << i5);
        long j3 = j2 >>> (64 - i5);
        if (j3 != 0) {
            int i6 = i4 + 1;
            jArr[i6] = j3 ^ jArr[i6];
        }
    }

    private static void interleave(long[] jArr, int i2, long[] jArr2, int i3, int i4, int i5) {
        if (i5 == 3) {
            interleave3(jArr, i2, jArr2, i3, i4);
            return;
        }
        if (i5 == 5) {
            interleave5(jArr, i2, jArr2, i3, i4);
        } else if (i5 != 7) {
            interleave2_n(jArr, i2, jArr2, i3, i4, bitLengths[i5] - 1);
        } else {
            interleave7(jArr, i2, jArr2, i3, i4);
        }
    }

    private static long interleave2_32to64(int i2) {
        short[] sArr = INTERLEAVE2_TABLE;
        int i3 = sArr[i2 & 255] | (sArr[(i2 >>> 8) & 255] << 16);
        return (((long) i3) & BodyPartID.bodyIdMax) | ((((long) ((sArr[i2 >>> 24] << 16) | sArr[(i2 >>> 16) & 255])) & BodyPartID.bodyIdMax) << 32);
    }

    private static void interleave2_n(long[] jArr, int i2, long[] jArr2, int i3, int i4, int i5) {
        for (int i6 = 0; i6 < i4; i6++) {
            jArr2[i3 + i6] = interleave2_n(jArr[i2 + i6], i5);
        }
    }

    private static void interleave3(long[] jArr, int i2, long[] jArr2, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            jArr2[i3 + i5] = interleave3(jArr[i2 + i5]);
        }
    }

    private static long interleave3_13to65(int i2) {
        int[] iArr = INTERLEAVE5_TABLE;
        int i3 = iArr[i2 & CertificateBody.profileType];
        return (((long) i3) & BodyPartID.bodyIdMax) | ((((long) iArr[i2 >>> 7]) & BodyPartID.bodyIdMax) << 35);
    }

    private static long interleave3_21to63(int i2) {
        int[] iArr = INTERLEAVE3_TABLE;
        int i3 = iArr[i2 & CertificateBody.profileType];
        return (((long) i3) & BodyPartID.bodyIdMax) | ((((long) iArr[i2 >>> 14]) & BodyPartID.bodyIdMax) << 42) | ((((long) iArr[(i2 >>> 7) & CertificateBody.profileType]) & BodyPartID.bodyIdMax) << 21);
    }

    private static long interleave4_16to64(int i2) {
        int[] iArr = INTERLEAVE4_TABLE;
        int i3 = iArr[i2 & 255];
        return (((long) i3) & BodyPartID.bodyIdMax) | ((((long) iArr[i2 >>> 8]) & BodyPartID.bodyIdMax) << 32);
    }

    private static void interleave5(long[] jArr, int i2, long[] jArr2, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            jArr2[i3 + i5] = interleave5(jArr[i2 + i5]);
        }
    }

    private static void interleave7(long[] jArr, int i2, long[] jArr2, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            jArr2[i3 + i5] = interleave7(jArr[i2 + i5]);
        }
    }

    private static void multiplyWord(long j2, long[] jArr, int i2, long[] jArr2, int i3) {
        if ((j2 & 1) != 0) {
            add(jArr2, i3, jArr, 0, i2);
        }
        int i4 = 1;
        long j3 = j2;
        while (true) {
            long j4 = j3 >>> 1;
            if (j4 == 0) {
                return;
            }
            if ((j4 & 1) != 0) {
                long jAddShiftedUp = addShiftedUp(jArr2, i3, jArr, 0, i2, i4);
                if (jAddShiftedUp != 0) {
                    int i5 = i3 + i2;
                    jArr2[i5] = jAddShiftedUp ^ jArr2[i5];
                }
            }
            i4++;
            j3 = j4;
        }
    }

    private static void reduceBit(long[] jArr, int i2, int i3, int i4, int[] iArr) {
        flipBit(jArr, i2, i3);
        int i5 = i3 - i4;
        int length = iArr.length;
        while (true) {
            length--;
            if (length < 0) {
                flipBit(jArr, i2, i5);
                return;
            }
            flipBit(jArr, i2, iArr[length] + i5);
        }
    }

    private static void reduceBitWise(long[] jArr, int i2, int i3, int i4, int[] iArr) {
        while (true) {
            i3--;
            if (i3 < i4) {
                return;
            }
            if (testBit(jArr, i2, i3)) {
                reduceBit(jArr, i2, i3, i4, iArr);
            }
        }
    }

    private static int reduceInPlace(long[] jArr, int i2, int i3, int i4, int[] iArr) {
        int i5 = (i4 + 63) >>> 6;
        if (i3 < i5) {
            return i3;
        }
        int i6 = i3 << 6;
        int iMin = Math.min(i6, (i4 << 1) - 1);
        int i7 = i6 - iMin;
        int i8 = i3;
        while (i7 >= 64) {
            i8--;
            i7 -= 64;
        }
        int length = iArr.length;
        int i9 = iArr[length - 1];
        int i10 = length > 1 ? iArr[length - 2] : 0;
        int iMax = Math.max(i4, i9 + 64);
        int iMin2 = (i7 + Math.min(iMin - iMax, i4 - i10)) >> 6;
        if (iMin2 > 1) {
            int i11 = i8 - iMin2;
            reduceVectorWise(jArr, i2, i8, i11, i4, iArr);
            while (i8 > i11) {
                i8--;
                jArr[i2 + i8] = 0;
            }
            iMin = i11 << 6;
        }
        if (iMin > iMax) {
            reduceWordWise(jArr, i2, i8, iMax, i4, iArr);
        } else {
            iMax = iMin;
        }
        if (iMax > i4) {
            reduceBitWise(jArr, i2, iMax, i4, iArr);
        }
        return i5;
    }

    private static LongArray reduceResult(long[] jArr, int i2, int i3, int i4, int[] iArr) {
        return new LongArray(jArr, i2, reduceInPlace(jArr, i2, i3, i4, iArr));
    }

    private static void reduceVectorWise(long[] jArr, int i2, int i3, int i4, int i5, int[] iArr) {
        int i6 = (i4 << 6) - i5;
        int length = iArr.length;
        while (true) {
            length--;
            if (length < 0) {
                flipVector(jArr, i2, jArr, i2 + i4, i3 - i4, i6);
                return;
            }
            flipVector(jArr, i2, jArr, i2 + i4, i3 - i4, i6 + iArr[length]);
        }
    }

    private static void reduceWord(long[] jArr, int i2, int i3, long j2, int i4, int[] iArr) {
        int i5 = i3 - i4;
        int length = iArr.length;
        while (true) {
            length--;
            if (length < 0) {
                flipWord(jArr, i2, i5, j2);
                return;
            }
            flipWord(jArr, i2, iArr[length] + i5, j2);
        }
    }

    private static void reduceWordWise(long[] jArr, int i2, int i3, int i4, int i5, int[] iArr) {
        int i6 = i4 >>> 6;
        int i7 = i3;
        while (true) {
            int i8 = i7 - 1;
            if (i8 <= i6) {
                break;
            }
            int i9 = i2 + i8;
            long j2 = jArr[i9];
            if (j2 != 0) {
                jArr[i9] = 0;
                reduceWord(jArr, i2, i8 << 6, j2, i5, iArr);
            }
            i7 = i8;
        }
        int i10 = i4 & 63;
        int i11 = i2 + i6;
        long j3 = jArr[i11];
        long j4 = j3 >>> i10;
        if (j4 != 0) {
            jArr[i11] = (j4 << i10) ^ j3;
            reduceWord(jArr, i2, i4, j4, i5, iArr);
        }
    }

    private long[] resizedInts(int i2) {
        long[] jArr = new long[i2];
        long[] jArr2 = this.m_ints;
        System.arraycopy(jArr2, 0, jArr, 0, Math.min(jArr2.length, i2));
        return jArr;
    }

    private static long shiftUp(long[] jArr, int i2, int i3, int i4) {
        int i5 = 64 - i4;
        long j2 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = i2 + i6;
            long j3 = jArr[i7];
            jArr[i7] = j2 | (j3 << i4);
            j2 = j3 >>> i5;
        }
        return j2;
    }

    private static void squareInPlace(long[] jArr, int i2, int i3, int[] iArr) {
        int i4 = i2 << 1;
        while (true) {
            i2--;
            if (i2 < 0) {
                return;
            }
            long j2 = jArr[i2];
            jArr[i4 - 1] = interleave2_32to64((int) (j2 >>> 32));
            i4 -= 2;
            jArr[i4] = interleave2_32to64((int) j2);
        }
    }

    private static boolean testBit(long[] jArr, int i2, int i3) {
        return (jArr[i2 + (i3 >>> 6)] & (1 << (i3 & 63))) != 0;
    }

    public LongArray addOne() {
        if (this.m_ints.length == 0) {
            return new LongArray(new long[]{1});
        }
        long[] jArrResizedInts = resizedInts(Math.max(1, getUsedLength()));
        jArrResizedInts[0] = 1 ^ jArrResizedInts[0];
        return new LongArray(jArrResizedInts);
    }

    public void addShiftedByWords(LongArray longArray, int i2) {
        int usedLength = longArray.getUsedLength();
        if (usedLength == 0) {
            return;
        }
        int i3 = usedLength + i2;
        if (i3 > this.m_ints.length) {
            this.m_ints = resizedInts(i3);
        }
        add(this.m_ints, i2, longArray.m_ints, 0, usedLength);
    }

    public Object clone() {
        return new LongArray(Arrays.clone(this.m_ints));
    }

    public int degree() {
        int length = this.m_ints.length;
        while (length != 0) {
            length--;
            long j2 = this.m_ints[length];
            if (j2 != 0) {
                return (length << 6) + bitLength(j2);
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LongArray)) {
            return false;
        }
        LongArray longArray = (LongArray) obj;
        int usedLength = getUsedLength();
        if (longArray.getUsedLength() != usedLength) {
            return false;
        }
        for (int i2 = 0; i2 < usedLength; i2++) {
            if (this.m_ints[i2] != longArray.m_ints[i2]) {
                return false;
            }
        }
        return true;
    }

    public int getLength() {
        return this.m_ints.length;
    }

    public int getUsedLength() {
        return getUsedLengthFrom(this.m_ints.length);
    }

    public int getUsedLengthFrom(int i2) {
        long[] jArr = this.m_ints;
        int iMin = Math.min(i2, jArr.length);
        if (iMin < 1) {
            return 0;
        }
        if (jArr[0] != 0) {
            while (true) {
                int i3 = iMin - 1;
                if (jArr[i3] != 0) {
                    return iMin;
                }
                iMin = i3;
            }
        } else {
            while (true) {
                int i4 = iMin - 1;
                if (jArr[i4] != 0) {
                    return iMin;
                }
                if (i4 <= 0) {
                    return 0;
                }
                iMin = i4;
            }
        }
    }

    public int hashCode() {
        int usedLength = getUsedLength();
        int i2 = 1;
        for (int i3 = 0; i3 < usedLength; i3++) {
            long j2 = this.m_ints[i3];
            i2 = (((i2 * 31) ^ ((int) j2)) * 31) ^ ((int) (j2 >>> 32));
        }
        return i2;
    }

    public boolean isOne() {
        long[] jArr = this.m_ints;
        if (jArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < jArr.length; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isZero() {
        for (long j2 : this.m_ints) {
            if (j2 != 0) {
                return false;
            }
        }
        return true;
    }

    public LongArray modInverse(int i2, int[] iArr) {
        int iDegreeFrom = 0;
        int iDegree = degree();
        if (iDegree == 0) {
            throw new IllegalStateException();
        }
        if (iDegree == 1) {
            return this;
        }
        LongArray longArray = (LongArray) clone();
        int i3 = (i2 + 63) >>> 6;
        LongArray longArray2 = new LongArray(i3);
        reduceBit(longArray2.m_ints, 0, i2, i2, iArr);
        LongArray longArray3 = new LongArray(i3);
        longArray3.m_ints[0] = 1;
        LongArray longArray4 = new LongArray(i3);
        int[] iArr2 = new int[2];
        iArr2[0] = iDegree;
        iArr2[1] = i2 + 1;
        LongArray[] longArrayArr = {longArray, longArray2};
        int[] iArr3 = new int[2];
        iArr3[0] = 1;
        iArr3[1] = 0;
        LongArray[] longArrayArr2 = {longArray3, longArray4};
        int i4 = iArr2[1];
        int i5 = i4 - iArr2[0];
        int i6 = 1;
        while (true) {
            if (i5 < 0) {
                i5 = -i5;
                iArr2[i6] = i4;
                iArr3[i6] = iDegreeFrom;
                i6 = 1 - i6;
                i4 = iArr2[i6];
                iDegreeFrom = iArr3[i6];
            }
            int i7 = 1 - i6;
            longArrayArr[i6].addShiftedByBitsSafe(longArrayArr[i7], iArr2[i7], i5);
            int iDegreeFrom2 = longArrayArr[i6].degreeFrom(i4);
            if (iDegreeFrom2 == 0) {
                return longArrayArr2[i7];
            }
            int i8 = iArr3[i7];
            longArrayArr2[i6].addShiftedByBitsSafe(longArrayArr2[i7], i8, i5);
            int i9 = i8 + i5;
            if (i9 > iDegreeFrom) {
                iDegreeFrom = i9;
            } else if (i9 == iDegreeFrom) {
                iDegreeFrom = longArrayArr2[i6].degreeFrom(iDegreeFrom);
            }
            i5 += iDegreeFrom2 - i4;
            i4 = iDegreeFrom2;
        }
    }

    public LongArray modMultiply(LongArray longArray, int i2, int[] iArr) {
        int i3;
        int i4;
        LongArray longArray2;
        LongArray longArray3;
        int i5;
        int i6;
        long[] jArr;
        int i7;
        int iDegree = degree();
        if (iDegree == 0) {
            return this;
        }
        int iDegree2 = longArray.degree();
        if (iDegree2 == 0) {
            return longArray;
        }
        if (iDegree > iDegree2) {
            i4 = iDegree;
            i3 = iDegree2;
            longArray3 = this;
            longArray2 = longArray;
        } else {
            i3 = iDegree;
            i4 = iDegree2;
            longArray2 = this;
            longArray3 = longArray;
        }
        int i8 = (i3 + 63) >>> 6;
        int i9 = (i4 + 63) >>> 6;
        int i10 = ((i3 + i4) + 62) >>> 6;
        if (i8 == 1) {
            long j2 = longArray2.m_ints[0];
            if (j2 == 1) {
                return longArray3;
            }
            long[] jArr2 = new long[i10];
            multiplyWord(j2, longArray3.m_ints, i9, jArr2, 0);
            return reduceResult(jArr2, 0, i10, i2, iArr);
        }
        int i11 = (i4 + 70) >>> 6;
        int[] iArr2 = new int[16];
        int i12 = i11 << 4;
        long[] jArr3 = new long[i12];
        iArr2[1] = i11;
        System.arraycopy(longArray3.m_ints, 0, jArr3, i11, i9);
        int i13 = 2;
        int i14 = i11;
        for (int i15 = 16; i13 < i15; i15 = 16) {
            i14 += i11;
            iArr2[i13] = i14;
            if ((i13 & 1) == 0) {
                jArr = jArr3;
                i7 = i12;
                shiftUp(jArr3, i14 >>> 1, jArr3, i14, i11, 1);
            } else {
                jArr = jArr3;
                i7 = i12;
                add(jArr, i11, jArr3, i14 - i11, jArr, i14, i11);
            }
            i13++;
            i12 = i7;
            jArr3 = jArr;
        }
        long[] jArr4 = jArr3;
        int i16 = i12;
        long[] jArr5 = new long[i16];
        shiftUp(jArr4, 0, jArr5, 0, i16, 4);
        long[] jArr6 = longArray2.m_ints;
        int i17 = i10 << 3;
        long[] jArr7 = new long[i17];
        int i18 = 0;
        while (i18 < i8) {
            long j3 = jArr6[i18];
            int i19 = i18;
            while (true) {
                i5 = i17;
                int i20 = iArr2[((int) j3) & 15];
                int i21 = iArr2[((int) (j3 >>> 4)) & 15];
                i6 = i18;
                addBoth(jArr7, i19, jArr4, i20, jArr5, i21, i11);
                j3 >>>= 8;
                if (j3 == 0) {
                    break;
                }
                i19 += i10;
                i18 = i6;
                i17 = i5;
            }
            i18 = i6 + 1;
            i17 = i5;
        }
        while (true) {
            i17 -= i10;
            if (i17 == 0) {
                return reduceResult(jArr7, 0, i10, i2, iArr);
            }
            addShiftedUp(jArr7, i17 - i10, jArr7, i17, i10, 8);
        }
    }

    public LongArray modMultiplyAlt(LongArray longArray, int i2, int[] iArr) {
        int i3;
        int i4;
        LongArray longArray2;
        LongArray longArray3;
        int i5;
        int iDegree = degree();
        if (iDegree == 0) {
            return this;
        }
        int iDegree2 = longArray.degree();
        if (iDegree2 == 0) {
            return longArray;
        }
        if (iDegree > iDegree2) {
            i4 = iDegree;
            i3 = iDegree2;
            longArray3 = this;
            longArray2 = longArray;
        } else {
            i3 = iDegree;
            i4 = iDegree2;
            longArray2 = this;
            longArray3 = longArray;
        }
        int i6 = (i3 + 63) >>> 6;
        int i7 = (i4 + 63) >>> 6;
        int i8 = ((i3 + i4) + 62) >>> 6;
        if (i6 == 1) {
            long j2 = longArray2.m_ints[0];
            if (j2 == 1) {
                return longArray3;
            }
            long[] jArr = new long[i8];
            multiplyWord(j2, longArray3.m_ints, i7, jArr, 0);
            return reduceResult(jArr, 0, i8, i2, iArr);
        }
        int i9 = (i4 + 78) >>> 6;
        int i10 = i9 * 8;
        int[] iArr2 = new int[16];
        iArr2[0] = i6;
        int i11 = i6 + i10;
        iArr2[1] = i11;
        for (int i12 = 2; i12 < 16; i12++) {
            i11 += i8;
            iArr2[i12] = i11;
        }
        long[] jArr2 = new long[i11 + i8 + 1];
        int i13 = i10;
        interleave(longArray2.m_ints, 0, jArr2, 0, i6, 4);
        System.arraycopy(longArray3.m_ints, 0, jArr2, i6, i7);
        int i14 = i6;
        int i15 = 1;
        while (i15 < 8) {
            int i16 = i14 + i9;
            shiftUp(jArr2, i6, jArr2, i16, i9, i15);
            i15++;
            i14 = i16;
        }
        int i17 = 0;
        int i18 = 15;
        while (true) {
            int i19 = 0;
            do {
                int i20 = i6;
                long j3 = jArr2[i19] >>> i17;
                int i21 = 0;
                while (true) {
                    int i22 = ((int) j3) & i18;
                    if (i22 != 0) {
                        add(jArr2, iArr2[i22] + i19, jArr2, i20, i9);
                    }
                    i21++;
                    if (i21 == 8) {
                        break;
                    }
                    i20 += i9;
                    j3 >>>= 4;
                }
                i19++;
            } while (i19 < i6);
            i17 += 32;
            if (i17 < 64) {
                i5 = i13;
            } else {
                if (i17 >= 64) {
                    break;
                }
                i17 = 60;
                i5 = i13;
                i18 = 0;
            }
            shiftUp(jArr2, i6, i5, 8);
            i13 = i5;
        }
        int i23 = 16;
        while (true) {
            int i24 = i23 - 1;
            if (i24 <= 1) {
                return reduceResult(jArr2, iArr2[1], i8, i2, iArr);
            }
            if ((((long) i24) & 1) == 0) {
                addShiftedUp(jArr2, iArr2[i24 >>> 1], jArr2, iArr2[i24], i8, 16);
            } else {
                distribute(jArr2, iArr2[i24], iArr2[i23 - 2], iArr2[1], i8);
            }
            i23 = i24;
        }
    }

    public LongArray modMultiplyLD(LongArray longArray, int i2, int[] iArr) {
        int i3;
        int i4;
        LongArray longArray2;
        LongArray longArray3;
        long[] jArr;
        int i5;
        int iDegree = degree();
        if (iDegree == 0) {
            return this;
        }
        int iDegree2 = longArray.degree();
        if (iDegree2 == 0) {
            return longArray;
        }
        if (iDegree > iDegree2) {
            i4 = iDegree;
            i3 = iDegree2;
            longArray3 = this;
            longArray2 = longArray;
        } else {
            i3 = iDegree;
            i4 = iDegree2;
            longArray2 = this;
            longArray3 = longArray;
        }
        int i6 = (i3 + 63) >>> 6;
        int i7 = (i4 + 63) >>> 6;
        int i8 = ((i3 + i4) + 62) >>> 6;
        if (i6 == 1) {
            long j2 = longArray2.m_ints[0];
            if (j2 == 1) {
                return longArray3;
            }
            long[] jArr2 = new long[i8];
            multiplyWord(j2, longArray3.m_ints, i7, jArr2, 0);
            return reduceResult(jArr2, 0, i8, i2, iArr);
        }
        int i9 = (i4 + 70) >>> 6;
        int[] iArr2 = new int[16];
        int i10 = i9 << 4;
        long[] jArr3 = new long[i10];
        iArr2[1] = i9;
        System.arraycopy(longArray3.m_ints, 0, jArr3, i9, i7);
        int i11 = 2;
        int i12 = i9;
        while (i11 < 16) {
            i12 += i9;
            iArr2[i11] = i12;
            if ((i11 & 1) == 0) {
                jArr = jArr3;
                i5 = i10;
                shiftUp(jArr3, i12 >>> 1, jArr3, i12, i9, 1);
            } else {
                jArr = jArr3;
                i5 = i10;
                add(jArr, i9, jArr3, i12 - i9, jArr, i12, i9);
            }
            i11++;
            i10 = i5;
            jArr3 = jArr;
        }
        long[] jArr4 = jArr3;
        int i13 = i10;
        long[] jArr5 = new long[i13];
        shiftUp(jArr4, 0, jArr5, 0, i13, 4);
        long[] jArr6 = longArray2.m_ints;
        long[] jArr7 = new long[i8];
        for (int i14 = 56; i14 >= 0; i14 -= 8) {
            for (int i15 = 1; i15 < i6; i15 += 2) {
                int i16 = (int) (jArr6[i15] >>> i14);
                addBoth(jArr7, i15 - 1, jArr4, iArr2[i16 & 15], jArr5, iArr2[(i16 >>> 4) & 15], i9);
            }
            shiftUp(jArr7, 0, i8, 8);
        }
        for (int i17 = 56; i17 >= 0; i17 -= 8) {
            for (int i18 = 0; i18 < i6; i18 += 2) {
                int i19 = (int) (jArr6[i18] >>> i17);
                addBoth(jArr7, i18, jArr4, iArr2[i19 & 15], jArr5, iArr2[(i19 >>> 4) & 15], i9);
            }
            if (i17 > 0) {
                shiftUp(jArr7, 0, i8, 8);
            }
        }
        return reduceResult(jArr7, 0, i8, i2, iArr);
    }

    public LongArray modReduce(int i2, int[] iArr) {
        long[] jArrClone = Arrays.clone(this.m_ints);
        return new LongArray(jArrClone, 0, reduceInPlace(jArrClone, 0, jArrClone.length, i2, iArr));
    }

    public LongArray modSquare(int i2, int[] iArr) {
        int usedLength = getUsedLength();
        if (usedLength == 0) {
            return this;
        }
        int i3 = usedLength << 1;
        long[] jArr = new long[i3];
        int i4 = 0;
        while (i4 < i3) {
            long j2 = this.m_ints[i4 >>> 1];
            int i5 = i4 + 1;
            jArr[i4] = interleave2_32to64((int) j2);
            i4 += 2;
            jArr[i5] = interleave2_32to64((int) (j2 >>> 32));
        }
        return new LongArray(jArr, 0, reduceInPlace(jArr, 0, i3, i2, iArr));
    }

    public LongArray modSquareN(int i2, int i3, int[] iArr) {
        int usedLength = getUsedLength();
        if (usedLength == 0) {
            return this;
        }
        int i4 = ((i3 + 63) >>> 6) << 1;
        long[] jArr = new long[i4];
        System.arraycopy(this.m_ints, 0, jArr, 0, usedLength);
        while (true) {
            i2--;
            if (i2 < 0) {
                return new LongArray(jArr, 0, usedLength);
            }
            squareInPlace(jArr, usedLength, i3, iArr);
            usedLength = reduceInPlace(jArr, 0, i4, i3, iArr);
        }
    }

    public LongArray multiply(LongArray longArray, int i2, int[] iArr) {
        int i3;
        int i4;
        LongArray longArray2;
        LongArray longArray3;
        long[] jArr;
        int i5;
        int iDegree = degree();
        if (iDegree == 0) {
            return this;
        }
        int iDegree2 = longArray.degree();
        if (iDegree2 == 0) {
            return longArray;
        }
        if (iDegree > iDegree2) {
            i4 = iDegree;
            i3 = iDegree2;
            longArray3 = this;
            longArray2 = longArray;
        } else {
            i3 = iDegree;
            i4 = iDegree2;
            longArray2 = this;
            longArray3 = longArray;
        }
        int i6 = (i3 + 63) >>> 6;
        int i7 = (i4 + 63) >>> 6;
        int i8 = ((i3 + i4) + 62) >>> 6;
        if (i6 == 1) {
            long j2 = longArray2.m_ints[0];
            if (j2 == 1) {
                return longArray3;
            }
            long[] jArr2 = new long[i8];
            multiplyWord(j2, longArray3.m_ints, i7, jArr2, 0);
            return new LongArray(jArr2, 0, i8);
        }
        int i9 = (i4 + 70) >>> 6;
        int[] iArr2 = new int[16];
        int i10 = i9 << 4;
        long[] jArr3 = new long[i10];
        iArr2[1] = i9;
        System.arraycopy(longArray3.m_ints, 0, jArr3, i9, i7);
        int i11 = 2;
        int i12 = i9;
        for (int i13 = 16; i11 < i13; i13 = 16) {
            i12 += i9;
            iArr2[i11] = i12;
            if ((i11 & 1) == 0) {
                jArr = jArr3;
                i5 = i10;
                shiftUp(jArr3, i12 >>> 1, jArr3, i12, i9, 1);
            } else {
                jArr = jArr3;
                i5 = i10;
                add(jArr, i9, jArr, i12 - i9, jArr3, i12, i9);
            }
            i11++;
            i10 = i5;
            jArr3 = jArr;
        }
        long[] jArr4 = jArr3;
        int i14 = i10;
        long[] jArr5 = new long[i14];
        shiftUp(jArr4, 0, jArr5, 0, i14, 4);
        long[] jArr6 = longArray2.m_ints;
        int i15 = i8 << 3;
        long[] jArr7 = new long[i15];
        for (int i16 = 0; i16 < i6; i16++) {
            int i17 = i16;
            long j3 = jArr6[i16];
            while (true) {
                addBoth(jArr7, i17, jArr4, iArr2[((int) j3) & 15], jArr5, iArr2[((int) (j3 >>> 4)) & 15], i9);
                j3 >>>= 8;
                if (j3 == 0) {
                    break;
                }
                i17 += i8;
            }
        }
        while (true) {
            i15 -= i8;
            if (i15 == 0) {
                return new LongArray(jArr7, 0, i8);
            }
            addShiftedUp(jArr7, i15 - i8, jArr7, i15, i8, 8);
        }
    }

    public void reduce(int i2, int[] iArr) {
        long[] jArr = this.m_ints;
        int iReduceInPlace = reduceInPlace(jArr, 0, jArr.length, i2, iArr);
        if (iReduceInPlace < jArr.length) {
            long[] jArr2 = new long[iReduceInPlace];
            this.m_ints = jArr2;
            System.arraycopy(jArr, 0, jArr2, 0, iReduceInPlace);
        }
    }

    public LongArray square(int i2, int[] iArr) {
        int usedLength = getUsedLength();
        if (usedLength == 0) {
            return this;
        }
        int i3 = usedLength << 1;
        long[] jArr = new long[i3];
        int i4 = 0;
        while (i4 < i3) {
            long j2 = this.m_ints[i4 >>> 1];
            int i5 = i4 + 1;
            jArr[i4] = interleave2_32to64((int) j2);
            i4 += 2;
            jArr[i5] = interleave2_32to64((int) (j2 >>> 32));
        }
        return new LongArray(jArr, 0, i3);
    }

    public boolean testBitZero() {
        long[] jArr = this.m_ints;
        return jArr.length > 0 && (1 & jArr[0]) != 0;
    }

    public BigInteger toBigInteger() {
        int usedLength = getUsedLength();
        if (usedLength == 0) {
            return ECConstants.ZERO;
        }
        int i2 = usedLength - 1;
        long j2 = this.m_ints[i2];
        byte[] bArr = new byte[8];
        int i3 = 0;
        boolean z2 = false;
        for (int i4 = 7; i4 >= 0; i4--) {
            byte b2 = (byte) (j2 >>> (i4 * 8));
            if (z2 || b2 != 0) {
                bArr[i3] = b2;
                i3++;
                z2 = true;
            }
        }
        byte[] bArr2 = new byte[(i2 * 8) + i3];
        for (int i5 = 0; i5 < i3; i5++) {
            bArr2[i5] = bArr[i5];
        }
        for (int i6 = usedLength - 2; i6 >= 0; i6--) {
            long j3 = this.m_ints[i6];
            int i7 = 7;
            while (i7 >= 0) {
                bArr2[i3] = (byte) (j3 >>> (i7 * 8));
                i7--;
                i3++;
            }
        }
        return new BigInteger(1, bArr2);
    }

    public String toString() {
        int usedLength = getUsedLength();
        if (usedLength == 0) {
            return "0";
        }
        int i2 = usedLength - 1;
        StringBuffer stringBuffer = new StringBuffer(Long.toBinaryString(this.m_ints[i2]));
        while (true) {
            i2--;
            if (i2 < 0) {
                return stringBuffer.toString();
            }
            String binaryString = Long.toBinaryString(this.m_ints[i2]);
            int length = binaryString.length();
            if (length < 64) {
                stringBuffer.append(ZEROES.substring(length));
            }
            stringBuffer.append(binaryString);
        }
    }

    private static void add(long[] jArr, int i2, long[] jArr2, int i3, long[] jArr3, int i4, int i5) {
        for (int i6 = 0; i6 < i5; i6++) {
            jArr3[i4 + i6] = jArr[i2 + i6] ^ jArr2[i3 + i6];
        }
    }

    private static long interleave2_n(long j2, int i2) {
        while (i2 > 1) {
            i2 -= 2;
            j2 = (interleave4_16to64(((int) (j2 >>> 48)) & 65535) << 3) | (interleave4_16to64(((int) (j2 >>> 16)) & 65535) << 1) | interleave4_16to64(((int) j2) & 65535) | (interleave4_16to64(((int) (j2 >>> 32)) & 65535) << 2);
        }
        if (i2 <= 0) {
            return j2;
        }
        return (interleave2_32to64((int) (j2 >>> 32)) << 1) | interleave2_32to64((int) j2);
    }

    private static long interleave3(long j2) {
        return (interleave3_21to63(((int) (j2 >>> 42)) & 2097151) << 2) | (Long.MIN_VALUE & j2) | interleave3_21to63(((int) j2) & 2097151) | (interleave3_21to63(((int) (j2 >>> 21)) & 2097151) << 1);
    }

    private static long interleave5(long j2) {
        return (interleave3_13to65(((int) (j2 >>> 52)) & 8191) << 4) | interleave3_13to65(((int) j2) & 8191) | (interleave3_13to65(((int) (j2 >>> 13)) & 8191) << 1) | (interleave3_13to65(((int) (j2 >>> 26)) & 8191) << 2) | (interleave3_13to65(((int) (j2 >>> 39)) & 8191) << 3);
    }

    private static long interleave7(long j2) {
        long[] jArr = INTERLEAVE7_TABLE;
        return (jArr[((int) (j2 >>> 54)) & 511] << 6) | (Long.MIN_VALUE & j2) | jArr[((int) j2) & 511] | (jArr[((int) (j2 >>> 9)) & 511] << 1) | (jArr[((int) (j2 >>> 18)) & 511] << 2) | (jArr[((int) (j2 >>> 27)) & 511] << 3) | (jArr[((int) (j2 >>> 36)) & 511] << 4) | (jArr[((int) (j2 >>> 45)) & 511] << 5);
    }

    public LongArray(long[] jArr) {
        this.m_ints = jArr;
    }

    private static long shiftUp(long[] jArr, int i2, long[] jArr2, int i3, int i4, int i5) {
        int i6 = 64 - i5;
        long j2 = 0;
        for (int i7 = 0; i7 < i4; i7++) {
            long j3 = jArr[i2 + i7];
            jArr2[i3 + i7] = j2 | (j3 << i5);
            j2 = j3 >>> i6;
        }
        return j2;
    }

    public LongArray(long[] jArr, int i2, int i3) {
        if (i2 == 0 && i3 == jArr.length) {
            this.m_ints = jArr;
            return;
        }
        long[] jArr2 = new long[i3];
        this.m_ints = jArr2;
        System.arraycopy(jArr, i2, jArr2, 0, i3);
    }

    public LongArray(BigInteger bigInteger) {
        int i2;
        if (bigInteger != null && bigInteger.signum() >= 0) {
            if (bigInteger.signum() == 0) {
                this.m_ints = new long[]{0};
                return;
            }
            byte[] byteArray = bigInteger.toByteArray();
            int length = byteArray.length;
            if (byteArray[0] == 0) {
                length--;
                i2 = 1;
            } else {
                i2 = 0;
            }
            int i3 = (length + 7) / 8;
            this.m_ints = new long[i3];
            int i4 = i3 - 1;
            int i5 = (length % 8) + i2;
            if (i2 < i5) {
                long j2 = 0;
                while (i2 < i5) {
                    j2 = (j2 << 8) | ((long) (byteArray[i2] & 255));
                    i2++;
                }
                this.m_ints[i4] = j2;
                i4 = i3 - 2;
            }
            while (i4 >= 0) {
                long j3 = 0;
                int i6 = 0;
                while (i6 < 8) {
                    j3 = (j3 << 8) | ((long) (byteArray[i2] & 255));
                    i6++;
                    i2++;
                }
                this.m_ints[i4] = j3;
                i4--;
            }
            return;
        }
        throw new IllegalArgumentException("invalid F2m field value");
    }
}
