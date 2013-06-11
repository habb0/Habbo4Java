package com.mmoscene.h4j.communication;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.events.catalog.*;
import com.mmoscene.h4j.communication.events.handshake.*;
import com.mmoscene.h4j.communication.events.hotelview.*;
import com.mmoscene.h4j.communication.events.inventory.SendInventoryItemsMessageEvent;
import com.mmoscene.h4j.communication.events.memenu.*;
import com.mmoscene.h4j.communication.events.messenger.*;
import com.mmoscene.h4j.communication.events.navigation.*;
import com.mmoscene.h4j.communication.events.room.*;
import com.mmoscene.h4j.communication.events.user.*;
import com.mmoscene.h4j.network.sessions.Session;
import gnu.trove.map.hash.THashMap;

public class CommunicationManager {
    THashMap<Integer, GameEvent> events = new THashMap<>();

    public CommunicationManager() {
        this.bindHandshake();
        this.bindUser();
        this.bindCatalog();
        this.bindMessenger();
        this.bindNavigator();
        this.bindHotelView();
        this.bindRooms();
        this.bindMeMenu();
        this.bindInventory();

        H4J.getLogger(CommunicationManager.class.getName()).info("Binded " + events.size() + " message events to their classes!");
    }

    private void bindHandshake() {
        events.put(H4J.getHeaders().getInt("ReadRevisionMessageEvent"), new ReadRevisionMessageEvent());
        events.put(H4J.getHeaders().getInt("InitializeCryptologyMessageEvent"), new InitializeCryptologyMessageEvent());
        events.put(H4J.getHeaders().getInt("SendSecretKeyMessageEvent"), new SendSecretKeyMessageEvent());
        events.put(H4J.getHeaders().getInt("SendUserTicketMessageEvent"), new SendUserTicketMessageEvent());
    }

    private void bindUser() {
        events.put(H4J.getHeaders().getInt("LoadUserInformationMessageEvent"), new LoadUserInformationMessageEvent());
        events.put(H4J.getHeaders().getInt("LoadUserClubMessageEvent"), new LoadUserClubMessageEvent());
        events.put(H4J.getHeaders().getInt("LoadUserProfileMessageEvent"), new LoadUserProfileMessageEvent());
        events.put(H4J.getHeaders().getInt("SendPermissionsMessageEvent"), new SendPermissionsMessageEvent());
        events.put(H4J.getHeaders().getInt("CompleteLoginMessageEvent"), new CompleteLoginMessageEvent());
    }

    private void bindCatalog() {
        events.put(H4J.getHeaders().getInt("LoadCatalogIndexMessageEvent"), new LoadCatalogIndexMessageEvent());
        events.put(H4J.getHeaders().getInt("LoadCatalogPageMessageEvent"), new LoadCatalogPageMessageEvent());
        events.put(H4J.getHeaders().getInt("PurchaseFromCatalogMessageEvent"), new PurchaseFromCatalogMessageEvent());
    }

    private void bindMessenger() {
        events.put(H4J.getHeaders().getInt("InitializeMessengerMessageEvent"), new InitializeMessengerMessageEvent());
        events.put(H4J.getHeaders().getInt("SendMessengerSearchMessageEvent"), new SendMessengerSearchMessageEvent());
        events.put(H4J.getHeaders().getInt("SendFriendRequestMessageEvent"), new SendFriendRequestMessageEvent());
        events.put(H4J.getHeaders().getInt("AcceptFriendRequestMessageEvent"), new AcceptFriendRequestMessageEvent());
        events.put(H4J.getHeaders().getInt("UpdateFriendStateMessageEvent"), new UpdateFriendStateMessageEvent());
        events.put(H4J.getHeaders().getInt("SendInstantMessageMessageEvent"), new SendInstantMessageMessageEvent());
    }

    private void bindNavigator() {
        events.put(H4J.getHeaders().getInt("RoomCreationMessageEvent"), new RoomCreationMessageEvent());
        events.put(H4J.getHeaders().getInt("RoomCreationCheckMessageEvent"), new RoomCreationCheckMessageEvent());
        events.put(H4J.getHeaders().getInt("SendUserRoomListMessageEvent"), new SendUserRoomListMessageEvent());
        events.put(H4J.getHeaders().getInt("CompleteNavigatorSearchMessageEvent"), new CompleteNavigatorSearchMessageEvent());
        events.put(H4J.getHeaders().getInt("SendFriendLocationListMessageEvent"), new SendFriendLocationListMessageEvent());
        events.put(H4J.getHeaders().getInt("SendRecentlyVisitedRoomListMessageEvent"), new SendRecentlyVisitedRoomListMessageEvent());
        events.put(H4J.getHeaders().getInt("SendFavoriteRoomListMessageEvent"), new SendFavoriteRoomListMessageEvent());
        events.put(H4J.getHeaders().getInt("SendFriendRoomListMessageEvent"), new SendFriendRoomListMessageEvent());
        events.put(H4J.getHeaders().getInt("SendPopulatedRoomsMessageEvent"), new SendPopulatedRoomsMessageEvent());
    }

    private void bindHotelView() {
        events.put(H4J.getHeaders().getInt("LoadPromotionalNewsMessageEvent"), new LoadPromotionalNewsMessageEvent());
        //events.put(H4J.getHeaders().getInt("SendHotelViewPieceMessageEvent"), new SendHotelViewPieceEvent());
    }

    private void bindRooms() {
        events.put(H4J.getHeaders().getInt("InitializeRoomMessageEvent"), new InitializeRoomMessageEvent());
        events.put(H4J.getHeaders().getInt("CompleteRoomLoadMessageEvent"), new CompleteRoomLoadMessageEvent());
        events.put(H4J.getHeaders().getInt("SendRoomSayMessageEvent"), new SendRoomSayMessageEvent());
        events.put(H4J.getHeaders().getInt("SendRoomShoutMessageEvent"), new SendRoomShoutMessageEvent());
        events.put(H4J.getHeaders().getInt("RoomActorWalkMessageEvent"), new RoomActorWalkMessageEvent());
        events.put(H4J.getHeaders().getInt("ShowSpeechBubbleStatusMessageEvent"), new ShowSpeechBubbleStatusMessageEvent());
        events.put(H4J.getHeaders().getInt("HideSpeechBubbleStatusMessageEvent"), new HideSpeechBubbleStatusMessageEvent());
    }

    private void bindMeMenu() {
        events.put(H4J.getHeaders().getInt("SendRoomUserSitStatusMessageEvent"), new SendRoomUserSitStatusMessageEvent());
        events.put(H4J.getHeaders().getInt("SendRoomUserActionStatusMessageEvent"), new SendRoomUserActionStatusMessageEvent());
        events.put(H4J.getHeaders().getInt("SendRoomUserDanceStatusMessageEvent"), new SendRoomUserDanceStatusMessageEvent());
        events.put(H4J.getHeaders().getInt("SendRoomUserSignStatusMessageEvent"), new SendRoomUserSignStatusMessageEvent());
        events.put(H4J.getHeaders().getInt("ValidateNewUsernameMessageEvent"), new ValidateNewUsernameMessageEvent());
        events.put(H4J.getHeaders().getInt("ModifyUserLooksMessageEvent"), new ModifyUserLooksMessageEvent());
        events.put(H4J.getHeaders().getInt("ModifyUserMottoMessageEvent"), new ModifyUserMottoMessageEvent());
        events.put(H4J.getHeaders().getInt("LoadUserRelationshipsMessageEvent"), new LoadUserRelationshipsMessageEvent());
    }

    private void bindInventory() {
        events.put(H4J.getHeaders().getInt("SendInventoryItemsMessageEvent"), new SendInventoryItemsMessageEvent());
    }

    public void parse(Session session, Request request) {
        if (!events.containsKey(request.readHeader())) {
            if (H4J.getConfig().get("log.communications").equals("true")) {
                H4J.getLogger(CommunicationManager.class.getName()).warn("[#" + request.readHeader() + "] " + request.body());
            }
        } else {

            GameEvent event = events.get(request.readHeader());

            if (H4J.getConfig().get("log.communications").equals("true")) {
                H4J.getLogger(event.getClass().getName()).info("[" + event.getClass().getSimpleName() + "] " + request.body());
            }

            event.parse(session, request);
        }
    }
}
