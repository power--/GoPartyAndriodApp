package com.goparty.app.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ServerProxy
{
	private int i = 0;
	private List<Map<String, Object>> list;
//	private Object obj;
	
	public void sendRequest(final ServerListener listener)
	{	
		final int num = i;
		if(i < 5)
		{
			Thread thread = new Thread() 
			{
				public void run() 
				{
					try
					{
						this.sleep(1000);
					}
					catch(Exception e)
					{
						
					}
					
					list = createData(num);
					if(i == 4)
					{
						listener.serverDataArrived(list, true);
					}
					else
					{
						listener.serverDataArrived(list, false);
					}
					
				}
			};
			thread.start();
			i++;
		}
		
		
	}
	
	private List<Map<String, Object>> createData(int i)
	{
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		
		if(i == 0)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("name", "Mo-Moƒ¡≥°");
			map.put("price", "»Àæ˘£∫Å0Ñ6147");
			map.put("addr", "ª¥∫£¬∑ »’ Ω◊‘÷˙");
			map.put("distance", "5.8km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "≥§ƒ˛«¯");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "≥ıª®");
			map.put("price", "»Àæ˘£∫Å0Ñ6285");
			map.put("addr", "∫Á«≈ »’±æ¡œ¿Ì");
			map.put("distance", "890m");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "≥§ƒ˛«¯");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "Ω≠±ﬂ≥«Õ‚Œ◊…Ω…’»´”„£®Ω¡Í∂´¬∑µÍ£©");
			map.put("price", "»Àæ˘£∫Å0Ñ660");
			map.put("addr", "»À√Òπ„≥° ¥®≤À");
			map.put("distance", "8.1km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", true);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "≥§ƒ˛«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "¬Ã≤Ë≤ÕÃ¸");
			map.put("price", "»Àæ˘£∫Å0Ñ660");
			map.put("addr", "¬≥—∏π´‘∞ ∫º∞Ô≤À");
			map.put("distance", "10km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 40);
			map.put("area", "’¢±±«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "…œœÌ≤ÕÃ¸");
			map.put("price", "»Àæ˘£∫Å0Ñ669");
			map.put("addr", "≥§ Ÿ¬∑ ≤Ë≤ÕÃ¸");
			map.put("distance", "5.0km");
			map.put("tuan", true);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "’¢±±«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "Õı∂¶»’±æ¡œ¿ÌÃ˙∞Â…’");
			map.put("price", "»Àæ˘£∫Å0Ñ6240");
			map.put("addr", "»À√Òπ„≥° »’±æ¡œ¿Ì");
			map.put("distance", "7.4km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "∆÷∂´–¬«¯");
			list.add(map);
		}
		
		if(i == 1)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("name", "ÁæøÕ");
			map.put("price", "»Àæ˘£∫Å0Ñ6136");
			map.put("addr", "Œ˜ ΩÃµ„");
			map.put("distance", "2.0km");
			map.put("tuan", true);
			map.put("promo", false);
			map.put("card", true);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "–Ïª„«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "æ∆ÕÃ");
			map.put("price", "»Àæ˘£∫Å0Ñ6324");
			map.put("addr", "¡˙∞ÿµÿ«¯ »’±æ¡œ¿Ì");
			map.put("distance", "4.0km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "«‡∆÷«¯");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "œƒ∂‰Œ˜≤ÕÃ¸");
			map.put("price", "»Àæ˘£∫Å0Ñ676");
			map.put("addr", "–Ïº“ª„ Œ˜ ΩºÚ≤Õ");
			map.put("distance", "4.4km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "À…Ω≠«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "Õ‚∆≈º“");
			map.put("price", "»Àæ˘£∫Å0Ñ656");
			map.put("addr", "ª≥µ’æ ∫º∞Ô≤À");
			map.put("distance", "8.0km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 40);
			map.put("area", "±¶…Ω«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "Ω≠±ﬂ≥«Õ‚Œ◊…Ω…’»´”„£®ƒœæ©Œ˜¬∑µÍ£©");
			map.put("price", "»Àæ˘£∫Å0Ñ663");
			map.put("addr", "ƒœæ©Œ˜¬∑ ¥®≤À");
			map.put("distance", "6.5km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", true);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "À…Ω≠«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "∞Ÿ∫œ∏£∫£œ …≈ ≥◊‹ª„£®»’‘¬π‚µÍ£©");
			map.put("price", "»Àæ˘£∫Å0Ñ6246");
			map.put("addr", "¥Ú∆÷«≈ ◊‘÷˙≤Õ");
			map.put("distance", "6.6km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "±¶…Ω«¯");
			list.add(map);
		}
		
		if(i == 2)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("name", "Ω«Æ±™£®—”∞≤Œ˜¬∑µÍ£©");
			map.put("price", "»Àæ˘£∫Å0Ñ6224");
			map.put("addr", "∂ØŒÔ‘∞/∫Á«≈ª˙≥° ◊‘÷˙≤Õ");
			map.put("distance", "2.9km");
			map.put("tuan", true);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 40);
			map.put("area", "–Ïª„«¯");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "≤º∏Ë∂´æ©£®∫Áø⁄¡˙÷Æ√ŒµÍ£©");
			map.put("price", "»Àæ˘£∫Å0Ñ627");
			map.put("addr", "¬≥—∏π´‘∞ Œ˜ ΩÃµ„");
			map.put("distance", "6.8km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "∫Áø⁄«¯");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "BLACK MAGIC CHOCOLATE");
			map.put("price", "»Àæ˘£∫Å0Ñ648");
			map.put("addr", "¥Ú∆÷«≈ Œ˜ ΩºÚ≤Õ");
			map.put("distance", "6.8km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "«‡∆÷«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "70∫Û∑π∞…");
			map.put("price", "»Àæ˘£∫Å0Ñ662");
			map.put("addr", "≥§ Ÿ¬∑ ±æ∞ÔΩ≠’„≤À");
			map.put("distance", "5.2km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 40);
			map.put("area", "—Ó∆÷«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "œ≤∂‡Œ›π˙º ∫£œ ª∆∆∑");
			map.put("price", "»Àæ˘£∫Å0Ñ6227");
			map.put("addr", "¬Ωº“◊Ï ◊‘÷˙≤Õ");
			map.put("distance", "10km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "–Ïª„«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "“Æ¿Ôœƒ¿ˆ–¬ΩÆ≤À£®’˛Õ®µÍ£©£©");
			map.put("price", "»Àæ˘£∫Å0Ñ669");
			map.put("addr", "ŒÂΩ«≥°/¥Û—ß«¯ –¬ΩÆ/«Â’Ê");
			map.put("distance", "6.6km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "–Ïª„«¯");
			list.add(map);
		}
		
		if(i == 3)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("name", "Œ—");
			map.put("price", "»Àæ˘£∫Å0Ñ667");
			map.put("addr", "æ≤∞≤À¬ øß∑»Ã¸");
			map.put("distance", "5.4km");
			map.put("tuan", true);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "≥§ƒ˛«¯");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "Ã©Â˙∏Û£®∫Áø⁄¡˙÷Æ√ŒµÍ£©");
			map.put("price", "»Àæ˘£∫Å0Ñ671");
			map.put("addr", "¬≥—∏π´‘∞ ∂´ƒœ—«≤À");
			map.put("distance", "10km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 40);
			map.put("area", "«‡∆÷«¯");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "»˝±¶÷‡∆Ã");
			map.put("price", "»Àæ˘£∫Å0Ñ629");
			map.put("addr", "»À√Òπ„≥° øÏ≤ÕºÚ≤Õ");
			map.put("distance", "8.1km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "—Ó∆÷«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "–°…Ω»’±æ¡œ¿Ì");
			map.put("price", "»Àæ˘£∫Å0Ñ6183");
			map.put("addr", "–¬ÃÏµÿ »’±æ¡œ¿Ì");
			map.put("distance", "7.4km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "∆÷∂´–¬«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "ƒ™∂˚∂Ÿ≈£≈≈∑ª");
			map.put("price", "»Àæ˘£∫Å0Ñ6602");
			map.put("addr", "¬Ωº“◊Ï ≈£≈≈");
			map.put("distance", "10km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 05);
			map.put("area", "∆÷∂´–¬«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "–°–°ª®‘∞");
			map.put("price", "»Àæ˘£∫Å0Ñ669");
			map.put("addr", "–Ïº“ª„ øß∑»");
			map.put("distance", "3.7km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "∆÷∂´–¬«¯");
			list.add(map);
		}
		
		if(i == 4)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("name", "∞¢æ√");
			map.put("price", "»Àæ˘£∫Å0Ñ6103");
			map.put("addr", "÷–…Ωπ´‘∞ »’±æ¡œ¿Ì");
			map.put("distance", "2.4km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 40);
			map.put("area", "–Ïª„«¯");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "“¨œ„ÃÏÃ√");
			map.put("price", "»Àæ˘£∫Å0Ñ6179");
			map.put("addr", "æ≤∞≤À¬ Ã©π˝≤À");
			map.put("distance", "5.1km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "–Ïª„«¯");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "”©∆ﬂ»Àº‰");
			map.put("price", "»Àæ˘£∫Å0Ñ6137");
			map.put("addr", "æ≤∞≤À¬ ¥¥“‚≤À");
			map.put("distance", "7.1km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "–Ïª„«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "œ…÷À–˘£®∑⁄—Ù¬∑µÍ£©");
			map.put("price", "»Àæ˘£∫Å0Ñ6457");
			map.put("addr", "“Ù¿÷—ß‘∫ »’±æ…’øæ");
			map.put("distance", "5.1km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "∫Áø⁄«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "◊Ø‘¥");
			map.put("price", "»Àæ˘£∫Å0Ñ6224");
			map.put("addr", "–¬ÃÏµÿ Œ˜∞‡—¿≤À");
			map.put("distance", "7.4km");
			map.put("tuan", true);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 40);
			map.put("area", "∫Áø⁄«¯");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "Ë÷◊”£®¬Ωº“◊ÏµÍ£©");
			map.put("price", "»Àæ˘£∫Å0Ñ6206");
			map.put("addr", "¬Ωº“◊Ï »’±æ¡œ¿Ì");
			map.put("distance", "10km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "∫Áø⁄«¯");
			list.add(map);
		}	
		return list;
	}
}