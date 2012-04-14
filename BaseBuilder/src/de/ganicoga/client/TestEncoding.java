package de.ganicoga.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestEncoding {

	public static void main(String[] args) {

		HuffmanTree tree = new HuffmanTree();

		String[] input = new String[]{
				"60G0B0C00000040500F05900900007040440H57A08800029A75700E00475940000000000",
				"60B000000040440500C09009500250440000778900010A757A0000072700000000000000",
				"0000H0G0600200A94000007570B04007570000948A94F05948770000040220C0000D0E00",
				"60G000000002594400B077A8990048475040F9909A800004077700C0002220000D000000",
				"0000J0G0604009A77900955755A05775777B008A0A84000490099F004904400000C0E0D0",
				"000000000010020110009700090B022A0000009760F1G01A220090008720100C00A0000H",
				"60D0E0B0C04400A200009007770009405570029487570009007900000402040000000000",
				"60G0B0000000005040F4947790000848A400I57A90000957757900CA7557A4000E9D0000",
				"0000J0G06000900440055A4809B95787790000948575F0094857A000007794C0000DAE00",
				"60G00B000000485A00F09497700049485500I4000779000000A940C57A75000957A7E0D0",
				"6000009A0040004727I09098755005504900B8770000004A500000C09500400000000000",
				"60J000000000040040B5709009095A000750C77004750055098889E7700444000D000900",
				"00H00G9A60005047570400987570090048AB04440077009889055F007570090000AD0E0C",
				"60G000000000002040B4940789007848A400F57A70000957750000C07550940000000000",
				"60G09J000000484940B577778909A7A22A40F5777790000E484950D0009005000C000000",
				"600000000000A72000B04870940094490000D00084A50000577700C4950550000E000900",
				"0000B0G060A757494005757A90F009A7575000447A75J00980890000000440C0000D9E00",
				"60D0C00F0000192700G0B17A78001917220001H800000000000010020002000020000000",
				"60GAF0J0C007570000B59995E00074447000A88988A00074447000059995000007570000",
				"60G0B0000000500000F0090005A004400977I98987A55044757779C0A755A4000E090D00",
				"60F0B070000559A520GA7577700027A00840E08400040049800900H0040400000C000000",
				"00G0J060F00400494000908880005570400007500900000A095500000407272000000A00",
				"000F060B00000404400059G9980005504740007779C00002A5500000000H010000000000",
				"60G0F0000040000040B09405900000075700E4057A700009875055C40480000000000000",
				"000BA0G0600577704009A75589F0480907400094040AJ04E09075700005A759000D0E00C",
				"0000B0G06000000000000494C0F009480940057A0A80I098757750044759759D0A757A0E",
				"0570B0G06957A0000000084944F049040900048009A7J0097A757A00570557C0000D9E00",
				"60G0B0000005000040F090059A004048A779I09487755049095757C004077A900E0D0A00",
				"9000B0G0655040000077899A75FA444A579009987704J00475000000A75900C0000D0E00",
				"60G0B0000040000040F09475900009A757A0I4057A7A7009875755C4948099000E0D0000",
				"0000B0G0600A7590400577557A9957A7775F008884A0000449000J4994000000000D0E0C",
				"0000B0G0600490075907A84A75F5570908009775A404H00A77990004905004C0000D0E00",
				"60G0B0000000040440F75999900A755A4040077779800J05A55800000977940C0E0AD000",
				"600000000000500000000900050004400879098907A55044057879000055040000000000",
				"0000B0G96000009440048757A8F94A75775900009A75I059400770000004A5C000D0E900",
				"0000B0G0604948A59000940777F00000A55900000975I070044800A9579400CD757A0E00",
				"60G000000000504000F40094940009489800I984A757005777757ACA755A90000E090D00",
				"60G0B0000000004000F49008950048040770907A9AA00J57575749007575840C0EA9D000",
				"60G0B0000000409000F490844000A7578900J4757A79009957A550C4000757000E0D9A00",
				"0000B0G06004A5004900977904F04055A89009877704J040A5590000097775C0000DAE00",
				};
		
		List<Integer> levelList = new ArrayList<Integer>();
		levelList.add(16);
		levelList.add(16);
		levelList.add(14);
		levelList.add(14);
		levelList.add(11);
		levelList.add(15);
		levelList.add(16);
		levelList.add(12);
		levelList.add(12);
		levelList.add(13);
		levelList.add(16);
		levelList.add(11);
		levelList.add(13);
		levelList.add(14);
		levelList.add(15);
		/*
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length(); j++) {
				
                if (!map.containsKey(input[i].charAt(j)))
                {
                	map.put(input[i].charAt(j), 0);
                }
                int value = map.get(input[i].charAt(j)) + 1;
                map.put(input[i].charAt(j), value);
			}
		}
		
		for(Map.Entry<Character, Integer> entry : map.entrySet()){
			System.out.println(entry.getKey() + " " + (int)Math.round(entry.getValue().doubleValue() / input.length));
		}
		
		System.out.println("Bases: " + input.length);
		
		System.out.println();
		
		String s = tree.encode(Util.oldString2IntList(input[0]));
		System.out.println(s);
		System.out.println(s.length());
		*/
		System.out.println();
		
		List<Byte> l = tree.encodeLevels(levelList);
		System.out.println(tree.decodeLevels(l).getFirstList().equals(levelList));
		

	}
}
