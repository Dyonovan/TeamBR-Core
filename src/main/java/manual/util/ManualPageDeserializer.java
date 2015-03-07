package manual.util;

import com.dyonovan.modernalchemy.manual.component.IComponent;
import com.google.gson.*;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ManualPageDeserializer implements JsonDeserializer<AbstractManualPage> {

    @Override
    public AbstractManualPage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        AbstractManualPage abstractManualPage = null;

        //JsonObject jsonObject = json.getAsJsonObject();
        JsonArray jsonArray = json.getAsJsonArray();
        for (int h = 0; h < jsonArray.size(); h++) {

            JsonObject jsonObject = jsonArray.get(h).getAsJsonObject();
            String title = jsonObject.get("title").getAsString();
            int pages = jsonObject.get("numPages").getAsInt();

            JsonArray ja = jsonObject.getAsJsonArray("component");
            ArrayList<AbstractComponent> mc = new ArrayList<AbstractComponent>();

            for (int i = 0; i < ja.size(); i++) {
                JsonObject je = ja.get(i).getAsJsonObject();
                int xPos = je.get("xPos").getAsInt();
                int yPos = je.get("yPos").getAsInt();
                int width = je.get("width").getAsInt();
                int height = je.get("height").getAsInt();
                int pageNum = je.get("pageNum").getAsInt();
                String text = je.get("text").getAsString();
                String destination = je.get("destination").getAsString();
                String type = je.get("type").getAsString();
                String item = je.get("item").getAsString();
                IComponent.ALIGNMENT alignment = IComponent.ALIGNMENT.values()[je.get("alignment").getAsInt()];

                JsonObject jo =  je.get("resource").getAsJsonObject();
                String resourceDomain = jo.get("resourceDomain").getAsString();
                String resourcePath = jo.get("resourcePath").getAsString();
                ResourceLocation rl = new ResourceLocation(resourceDomain, resourcePath);

                JsonArray jaTooltips = je.get("tooltips").getAsJsonArray();
                ArrayList<String> tooltips = new ArrayList<String>();
                for (int j = 0; j < jaTooltips.size(); j++) {
                    JsonElement jsonTT = jaTooltips.get(j);
                    tooltips.add(jsonTT.getAsString());
                }
                mc.add(new AbstractComponent(type, xPos, yPos, width, height, alignment, pageNum, text, destination, item, rl, tooltips));
            }
            abstractManualPage = new AbstractManualPage(title, pages, mc);
        }
        return abstractManualPage;
    }
}
