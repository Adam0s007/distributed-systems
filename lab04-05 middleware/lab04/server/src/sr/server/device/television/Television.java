package server.device.television;

import SmartHome.*;
import com.zeroc.Ice.Current;
import server.device.Device;

import java.util.Arrays;
import java.util.List;

public class Television extends Device implements ITelevision {
    private int currentChannel;
    private List<TVChannel> channelList;
    public Television(DeviceInfo deviceInfo) {
        super(deviceInfo);
        this.currentChannel = 0;
        this.channelList = Arrays.asList(
                new TVChannel("CNN", "24-hour news channel covering both domestic and international news."),
        new TVChannel( "ESPN", "Sports network providing live coverage of various sports events and sports-related news."),
        new TVChannel( "HBO", "Premium channel offering a wide range of movies, original TV shows, and documentaries."),
        new TVChannel( "Nickelodeon", "Children's channel featuring cartoons, live-action shows, and educational programs."),
        new TVChannel( "National Geographic", "Channel dedicated to documentaries with a focus on nature, science, culture, and history."),
        new TVChannel( "MTV", "Music channel featuring music videos, reality shows, and youth culture programming."),
        new TVChannel( "TCM", "Classic Movies channel offering films primarily from the Golden Age of Hollywood and beyond."),
        new TVChannel( "Comedy Central", "Channel specializing in all types of comedic formats including stand-up specials, sitcoms, and satirical news."),
        new TVChannel( "HGTV", "Home and Garden Television focusing on home improvement, gardening, craft, and remodeling."),
        new TVChannel( "Discovery", "Channel focusing on popular science, technology, and history content.")
        );
    }
    @Override
    public TVChannel getCurrentChannel(Current current) throws TelevisionOperationException {
        System.out.println("Method Television.getCurrentChannel with no args called by " + current.id.name + ", category: " + current.id.category);
        return channelList.get(currentChannel);
    }

    @Override
    public boolean setChannel(int newChannel, Current current) throws InvalidChannelException {
        System.out.println("Method Television.setChannel with args " +newChannel+ " called by " + current.id.name + ", category: " + current.id.category);
        if(newChannel < 0 || newChannel >= channelList.size()) {
            throw new InvalidChannelException("Invalid channel number","setChannel");
        }
        this.currentChannel = newChannel;
        return true;
    }

    @Override
    public List<TVChannel> getChannelList(Current current) {
        System.out.println("Method Television.getChannelList with no args called by " + current.id.name + ", category: " + current.id.category);
        return this.channelList;
    }

    @Override
    public String getDetails(Current current) throws SmarthomeException {
        return super.getDetails(current) + "current channel: " + channelList.get(currentChannel).name + ", description: " + channelList.get(currentChannel).description + " \n";
    }
}
