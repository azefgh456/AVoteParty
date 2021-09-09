package fr.acore.spigot.data;

import fr.acore.spigot.manager.AVotePartyManager;
import fr.acore.spigot.storage.constraint.QueryConstraint;
import fr.acore.spigot.storage.factory.DataFactory;
import fr.acore.spigot.utils.VoteParty;

public class AVotePartyFactory extends DataFactory<VoteParty, AVotePartyManager> {

    public AVotePartyFactory(AVotePartyManager manager) {
        super(VoteParty.class, manager);
    }

    @Override
    public void loadAll() {
        VoteParty voteParty = selectFirst(QueryConstraint.WHERE_ALL);
        if(voteParty != null){
            manager.setCurrentParty(voteParty.getName()).setCurrent(voteParty.getCurrent());
        }
    }

    @Override
    public void saveAll() {
        getTable().truncate();

        VoteParty currentParty = manager.getCurrentParty();
        save(currentParty);
    }

    @Override
    public void save(VoteParty obj) {
        insert(obj);
    }
}
