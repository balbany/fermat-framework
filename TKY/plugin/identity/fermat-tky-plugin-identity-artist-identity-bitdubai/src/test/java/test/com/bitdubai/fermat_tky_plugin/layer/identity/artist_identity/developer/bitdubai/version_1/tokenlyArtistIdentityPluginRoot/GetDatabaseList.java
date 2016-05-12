package test.com.bitdubai.fermat_tky_plugin.layer.identity.artist_identity.developer.bitdubai.version_1.tokenlyArtistIdentityPluginRoot;

import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperDatabase;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperObjectFactory;
import com.bitdubai.fermat_tky_api.all_definitions.exceptions.IdentityNotFoundException;
import com.bitdubai.fermat_tky_api.layer.identity.artist.exceptions.CantGetArtistIdentityException;
import com.bitdubai.fermat_tky_api.layer.identity.artist.interfaces.Artist;
import com.bitdubai.fermat_tky_plugin.layer.identity.artist_identity.developer.bitdubai.version_1.TokenlyArtistIdentityPluginRoot;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

/**
 * Created by gianco on 09/05/16.
 */
public class GetDatabaseList {
    @Mock
    List<DeveloperDatabase> developerDatabases;
    @Mock
    DeveloperObjectFactory developerObjectFactory;

    @Test
    public void getDatabaseList(){
        TokenlyArtistIdentityPluginRoot tokenlyArtistIdentityPluginRoot = Mockito.mock(TokenlyArtistIdentityPluginRoot.class);
        when(tokenlyArtistIdentityPluginRoot.getDatabaseList(developerObjectFactory)).thenReturn(developerDatabases);
    }
}
