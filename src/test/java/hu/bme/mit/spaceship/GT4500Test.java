package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryTorpedoStoreMock, secondaryTorpedoStoreMock;

  @BeforeEach
  public void init(){
    primaryTorpedoStoreMock = mock(TorpedoStore.class);
    secondaryTorpedoStoreMock = mock(TorpedoStore.class);
    this.ship = new GT4500(primaryTorpedoStoreMock, secondaryTorpedoStoreMock);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryTorpedoStoreMock.fire(1)).thenReturn(true);
    when(secondaryTorpedoStoreMock.fire(1)).thenReturn(true);
    when(primaryTorpedoStoreMock.isEmpty()).thenReturn(false);
    when(secondaryTorpedoStoreMock.isEmpty()).thenReturn(false);
  
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primaryTorpedoStoreMock, times(1)).fire(1);
    verify(secondaryTorpedoStoreMock, times(0)).fire(1);
    verify(primaryTorpedoStoreMock, times(1)).isEmpty();
    verify(secondaryTorpedoStoreMock, times(0)).isEmpty();

  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primaryTorpedoStoreMock.fire(1)).thenReturn(true);
    when(secondaryTorpedoStoreMock.fire(1)).thenReturn(true);
    when(primaryTorpedoStoreMock.isEmpty()).thenReturn(false, true);
    when(secondaryTorpedoStoreMock.isEmpty()).thenReturn(false, true);
  
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primaryTorpedoStoreMock, times(4)).fire(1);
    verify(secondaryTorpedoStoreMock, times(0)).fire(1);
    verify(primaryTorpedoStoreMock, times(3)).isEmpty();
    verify(secondaryTorpedoStoreMock, times(2)).isEmpty();
  }

}
